package ws.lumo.ai.orchestration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import ws.lumo.adapter.AdapterRegistryImpl;
import ws.lumo.ai.ChatMessage;
import ws.lumo.ai.Role;
import ws.lumo.api.AdapterRegistry;
import ws.lumo.api.history.ConversationHistoryApi;
import ws.lumo.api.provider.AdapterConfig;
import ws.lumo.api.provider.AdapterConfigurationProvider;
import ws.lumo.api.translate.LanguageDetectionResult;
import ws.lumo.api.translate.TranslationService;
import ws.lumo.billing.BillingApi;
import ws.lumo.api.escalation.EscalationTrackerApi;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AiOrchestratorServiceImplTest {

    @Mock
    private AdapterConfigurationProvider configurationProvider;
    @Mock
    private TranslationService translationService;
    @Mock
    private ConversationHistoryApi conversationHistoryApi;
    @Mock
    private BillingApi billingApi;
    @Mock
    private EscalationTrackerApi escalationTrackerApi;
    @Mock
    private OrchestrationPreProcessingService preProcessingService;
    @Mock
    private AiInteractionService aiInteractionService;
    @Mock
    private AdapterRegistry adapterRegistry;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private VectorStore vectorStore;

    @InjectMocks
    private AiOrchestratorServiceImpl orchestratorService;

    private AdapterConfig adapterConfig;
    private List<ChatMessage> history;

    @BeforeEach
    void setUp() {
        adapterConfig = new AdapterConfig("customer-1", "adapter-1", "JSON_CHAT_ADAPTER",
                Map.of("customerSystemPrompt", "You are a test assistant."),
                "You are a test assistant.", Collections.emptyList(), "Hi!", "escalation@test.com",
                "Please provide email", 2, "Too short", 7, 0.85, 10,
                false, false, "en", "key-123", 5, false);

        history = List.of(
                new ChatMessage(Role.USER, "Hello, what is Lumo?", Map.of("sessionId", "session-123"))
        );
    }

    @Test
    void generateCooperativeResponse_withVectorSearch_constructsPromptWithContext() {
        // Arrange
        when(configurationProvider.findConfigurationById("adapter-1")).thenReturn(Optional.of(adapterConfig));
        when(preProcessingService.preProcess(any(), any())).thenReturn(Optional.empty());

        // Mockataan vektoritietokannan haku
        List<Document> similarDocs = List.of(new Document("Lumo is an AI platform."));
        when(vectorStore.similaritySearch(any(org.springframework.ai.vectorstore.SearchRequest.class))).thenReturn(similarDocs);

        // Mockataan kielen tunnistus
        when(translationService.detectLanguage(anyString())).thenReturn(new LanguageDetectionResult("en", 0.99));

        // Mockataan AI-vastaus
        Generation generation = new Generation("Lumo is an AI platform that helps businesses.");
        ChatResponse chatResponse = new ChatResponse(List.of(generation));
        when(aiInteractionService.getAiResponse(any(), any(), any())).thenReturn(chatResponse);

        // Act
        OrchestrationResult result = orchestratorService.generateCooperativeResponse("adapter-1", history);

        // Assert
        // Varmistetaan, että vektoritietokantaa kutsuttiin
        verify(vectorStore, times(1)).similaritySearch(any(org.springframework.ai.vectorstore.SearchRequest.class));

        // Varmistetaan, että AI-palvelua kutsuttiin
        verify(aiInteractionService, times(1)).getAiResponse(eq("adapter-1"), any(), any());

        // Varmistetaan, että vastaus on oikea
        assertEquals("Lumo is an AI platform that helps businesses.", result.message().content());
        assertEquals("en", result.language());

        // Varmistetaan, että laskutettava vastaus kirjattiin
        verify(billingApi, times(1)).incrementBillableResponseCount("adapter-1");
    }
}