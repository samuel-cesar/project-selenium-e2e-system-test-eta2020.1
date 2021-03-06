package system.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static system.helpers.DriverManager.*;

//Domain Specific Language (DSL)
public class DSL {

    /* A Domain Specific Language (DSL)
    Esta linguagem abstrai completamente os conceitos de campos de entrada, botões, cliques e etc.
    Usando essa abordagem, tudo que um testador precisa fazer é chamar os métodos da DSL.
    Isso lhe dá uma vantagem de manutenção: se os campos de login mudassem, você só teria que mudar este método - não seus testes.
    https://www.selenium.dev/documentation/en/guidelines_and_recommendations/domain_specific_language/ */

    /********* Acessar Página - Get ************/

    public void acessarPagina(String site) {
        getDriver().get(site);
    }

    /********* TextField e TextArea ************/

    public void escrever(By by, String texto) {
        getDriver().findElement(by).clear();
        getDriver().findElement(by).sendKeys(texto);
    }

    public void escreverEApertarEnter(By by, String texto) {
        getDriver().findElement(by).clear();
        getDriver().findElement(by).sendKeys(texto + Keys.ENTER);
    }

    public void escreverId(String id_campo, String texto) {
        escrever(By.id(id_campo), texto);
    }

    public void escreverName(String name_campo, String texto) {
        escrever(By.name(name_campo), texto);
    }

    public void escreverXpath(String xpath_campo, String texto) {
        escrever(By.xpath(xpath_campo), texto);
    }

    public void escreverCss(String css_campo, String texto) {
        escrever(By.cssSelector(css_campo), texto);
    }

    public void escreverApertarEnter(String id_campo, String texto) {
        escreverEApertarEnter(By.id(id_campo), texto);
    }

    public String obterValorCampo(String id_campo) {
        return getDriver().findElement(By.id(id_campo)).getAttribute("value");
    }

    public String obterValorCampoXPath(String xpath_campo) {
        getDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath_campo)));
        return getDriver().findElement(By.xpath(xpath_campo)).getAttribute("value");
    }

    /********* Radio e Check ************/

    public void clicarRadio(String id) {
        getDriver().findElement(By.id(id)).click();
    }

    public boolean isRadioMarcado(String id) {
        return getDriver().findElement(By.id(id)).isSelected();
    }

    public void clicarCheck(String id) {
        getDriver().findElement(By.id(id)).click();
    }

    public boolean isCheckMarcado(String id) {
        return getDriver().findElement(By.id(id)).isSelected();
    }

    /********* Textos ************/

    public String obterTexto(By by) {
        return getDriver().findElement(by).getText();
    }

    public String obterTexto(String id) {
        return obterTexto(By.id(id));
    }

    public String obterTextoXPath(String xpath) {
        getDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        return obterTexto(By.xpath(xpath));
    }

    public String obterTextoCSS(String css) {
        return obterTexto(By.cssSelector(css));
    }

    public boolean getTextIsDisplayed(String id) {
        return getDriver().findElement(By.id(id)).isDisplayed();
    }

    /********* Combo ************/

    public void selecionarComboId(String id, String valor) {
        WebElement element = getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        combo.selectByVisibleText(valor);
    }

    public void selecionarComboXpath(String xpath, String valor) {
        getDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        WebElement element = getDriver().findElement(By.xpath(xpath));
        Select combo = new Select(element);
        combo.selectByVisibleText(valor);
    }

    public void deselecionarCombo(String id, String valor) {
        WebElement element = getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        combo.deselectByVisibleText(valor);
    }

    public String obterValorCombo(String id) {
        WebElement element = getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        return combo.getFirstSelectedOption().getText();
    }

    public List<String> obterValoresCombo(String id) {
        WebElement element = getDriver().findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);
        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
        List<String> valores = new ArrayList<String>();
        for (WebElement opcao : allSelectedOptions) {
            valores.add(opcao.getText());
        }
        return valores;
    }

    public int obterQuantidadeOpcoesCombo(String id) {
        WebElement element = getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();
        return options.size();
    }

    public boolean verificarOpcaoCombo(String id, String opcao) {
        WebElement element = getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();
        for (WebElement option : options) {
            if (option.getText().equals(opcao)) {
                return true;
            }
        }
        return false;
    }

    /********* Campo Search ************/

    public void clicarNoCampoSearch(String id) {
        getDriverWait().until(ExpectedConditions.elementToBeClickable(By.id(id)));
        getDriver().findElement(By.id(id)).click();
    }

    /********* Send US Feedback ************/

    public void clicarSendUSFeedBack(String xpath) {
        getDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        getDriver().findElement(By.xpath(xpath)).click();
    }

    /********* Submit ************/
    public void clicarSubmitXpath(String xpath) {
        getDriver().findElement(By.xpath(xpath)).click();
    }

    /********* SignIN ************/
    public void clicarBotaoSignIn(String signIn) {
        getDriver().findElement(By.xpath(signIn)).click();
    }

    public void clicarLabelIdioma(String labelIdioma) {
        getDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath(labelIdioma)));
        getDriver().findElement(By.xpath(labelIdioma)).click();
    }

    public void clicarPais(String elementCountry) {
        getDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath(elementCountry)));
        getDriver().findElement(By.xpath(elementCountry)).click();
    }

    public String verificarUrl() {
        return getDriver().getCurrentUrl();
    }

    public void apertarATeclaEnter(String searchInput) {
        getDriver().findElement(By.cssSelector(searchInput)).sendKeys(Keys.ENTER);
    }

    public void clicarProdutoAgasalho(String agasalhoXpath) {
        getDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath(agasalhoXpath)));
        getDriver().findElement(By.xpath(agasalhoXpath)).click();
    }

    public void selecionarTamanhoDoProdutoP(String tamanhoDoProdutoPXPath) {
        getDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath(tamanhoDoProdutoPXPath)));
        getDriver().findElement(By.xpath(tamanhoDoProdutoPXPath)).click();
    }

    public void clicarBotaoComprarXpath(String clicarBotaoComprar) {
        getDriver().findElement(By.xpath(clicarBotaoComprar)).click();
    }

    public void clicarBotaoMais(String clicarBotaoMaiQuantidadeXPath) {
        getDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath(clicarBotaoMaiQuantidadeXPath)));
        getDriver().findElement(By.xpath(clicarBotaoMaiQuantidadeXPath)).click();
    }
}
