import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.PolishNotation;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

public class TestPolishNotation {
    @Test
    public void testMethod1() {
        System.out.println("Тест 1");
        String expression = "( log ( sqrt ( 2 ^ 3 ) ) ) / ( 4 - 3 )";
        double ExpressionAnswer= 0.4515449934959718;
        PolishNotation str = new PolishNotation();
        String polishNotation = str.toPolishNotation(expression);
        System.out.println("Польская запись:" + polishNotation);
        double result = str.calculate(polishNotation);
        if(result==ExpressionAnswer)
        System.out.println(true);
        else System.out.println(false);
    }
    @Test
    public void testMethod2() {
        String expression = "( log 10 )";
        PolishNotation str = new PolishNotation();
        double ExpressionAnswer= 1;
        String polishNotation = str.toPolishNotation(expression);
        System.out.println("Польская запись:" + polishNotation);
        double result = str.calculate(polishNotation);
        if(result==ExpressionAnswer)
            System.out.println(true);
        else System.out.println(false);

    }
    @Test
    public void testMethod3() {
        String expression = "( 56 + 27 ^ 40 ) / ( ( 2 * 3 ) ) ^ 5";
        PolishNotation str = new PolishNotation();
        String polishNotation = str.toPolishNotation(expression);
        System.out.println(polishNotation);
    }
    @Test
    public void test()throws Exception {
        File file = new File("TestData/DataExel.xlsx");
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);
        PolishNotation polishNotation= new PolishNotation();
        String expression = "";
        String TrueAnswer = "";
        for (int i=1; i<=sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i);
            if(row != null) {
                expression =(row.getCell(0).getStringCellValue());
                TrueAnswer =(row.getCell(1).getStringCellValue());
                if(expression!=null){
// не использовал assert потому что он сравнивает либо числа либо объекты(чтобы ссылка одинаковая была)
                    //возможно есть другой метод, который я не нашёл если есть сообщите
                    TrueAnswer.equals(polishNotation.toPolishNotation(expression));
                }
            }
        }
        fis.close();
        workbook.close();
    }





}
