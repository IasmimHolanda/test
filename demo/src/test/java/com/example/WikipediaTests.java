import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import static org.junit.Assert.*;

public class WikipediaTests {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "caminho/do/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://pt.wikipedia.org/wiki/P%C3%A1gina_principal");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testBasicSearch() {
        WebElement searchInput = driver.findElement(By.id("searchInput"));
        searchInput.sendKeys("JUnit");
        searchInput.submit();

        // Verificação se os resultados são exibidos corretamente
        WebElement resultsTitle = driver.findElement(By.id("firstHeading"));
        assertEquals("Resultados da pesquisa incorretos", "JUnit", resultsTitle.getText());
    }

    @Test
    public void testPageNavigation() {
        // Clique em um link para outra página
        WebElement link = driver.findElement(By.linkText("História"));
        link.click();

        // Volte à página anterior
        driver.navigate().back();

        // Verificação se a navegação é suave e sem erros
        assertEquals("URL da página incorreta após voltar", "https://pt.wikipedia.org/wiki/P%C3%A1gina_principal", driver.getCurrentUrl());
    }

    @Test
    public void testEditPage() {
        // Clique no botão "Editar"
        WebElement editButton = driver.findElement(By.id("ca-edit"));
        editButton.click();

        // Realize uma edição simples (por exemplo, adicione um texto)
        WebElement editTextArea = driver.findElement(By.id("wpTextbox1"));
        editTextArea.sendKeys("Teste de edição usando Selenium");

        // Salve as alterações
        WebElement saveButton = driver.findElement(By.id("wpSave"));
        saveButton.click();

        // Verificação se as alterações foram salvas corretamente
        WebElement confirmationMessage = driver.findElement(By.className("mw-notification-success"));
        assertTrue("As alterações não foram salvas corretamente", confirmationMessage.isDisplayed());
    }
    @Test
    public void testRevisionHistory() {
        // Navegue até o histórico de revisões
        WebElement historyTab = driver.findElement(By.id("ca-history"));
        historyTab.click();

        // Verificação se o histórico de revisões é exibido corretamente
        WebElement historyHeading = driver.findElement(By.id("firstHeading"));
        assertEquals("Página de histórico de revisões incorreta", "Histórico de revisões", historyHeading.getText());
    }

    @Test
    public void testDiscussionPageAccess() {
        // Navegue até a página de discussão
        WebElement discussTab = driver.findElement(By.id("ca-talk"));
        discussTab.click();

        // Verificação se a página de discussão é acessível
        WebElement discussHeading = driver.findElement(By.id("firstHeading"));
        assertEquals("Página de discussão incorreta", "Discussão:Página principal", discussHeading.getText());
    }

    @Test
    public void testImageDisplay() {
        // Navegue até uma página com imagens
        driver.get("https://pt.wikipedia.org/wiki/Java");

        // Verificação se as imagens são exibidas corretamente
        List<WebElement> images = driver.findElements(By.tagName("img"));
        assertFalse("Nenhuma imagem encontrada na página", images.isEmpty());
    }
    @Test
    public void testSidebarFunctionality() {
        // Interaja com links ou ferramentas disponíveis na barra lateral
        WebElement randomLink = driver.findElement(By.id("n-randompage"));
        randomLink.click();

        // Verificação se a ação na barra lateral foi eficaz
        WebElement heading = driver.findElement(By.id("firstHeading"));
        assertTrue("Ação na barra lateral não foi eficaz", heading.isDisplayed());
    }

    @Test
    public void testAdvancedSearchFunctionality() {
        // Utilize a busca avançada para refinar uma consulta
        WebElement searchInput = driver.findElement(By.id("searchInput"));
        searchInput.sendKeys("Selenium");
        WebElement advancedSearchLink = driver.findElement(By.linkText("Busca avançada"));
        advancedSearchLink.click();

        // Verificação se os resultados refletem as configurações avançadas
        WebElement keywordField = driver.findElement(By.id("mw-search-advancedsearch-input"));
        assertEquals("Campo de palavra-chave incorreto", "Selenium", keywordField.getAttribute("value"));
    }

    @Test
    public void testCategoryNavigation() {
        // Navegue por categorias
        WebElement categoriesLink = driver.findElement(By.linkText("Categorias"));
        categoriesLink.click();
        
        // Verificação se a navegação por categorias é eficiente
        WebElement categoryHeading = driver.findElement(By.id("firstHeading"));
        assertEquals("Navegação por categorias incorreta", "Categorias", categoryHeading.getText());
    }

    @Test
    public void testExternalLinksValidity() {
        // Clique em links externos e verifique se a página de destino é acessível
        WebElement externalLink = driver.findElement(By.cssSelector("#mp-upper .external"));
        String linkUrl = externalLink.getAttribute("href");
        externalLink.click();

        // Verificação se a página de destino é acessível
        assertEquals("URL de destino incorreta após clicar no link externo", linkUrl, driver.getCurrentUrl());
    }

}
