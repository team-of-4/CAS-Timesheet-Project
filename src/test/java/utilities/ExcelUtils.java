package utilities;
 
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
 
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
public class ExcelUtils {
	public static void statusDetails(List<Boolean> optionStatus) throws IOException {
		String path = System.getProperty("user.dir") + "//timesheet_output.xlsx";
		FileOutputStream file = new FileOutputStream(path);
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet s = wb.createSheet("timesheet");
		String[] options = new String[] {"Approved", "Overdue", "Partially Approved", "Pending", "Saved", "Send Back For Revision", "Submitted For Approval"};
		XSSFRow heading = s.createRow(0);
		heading.createCell(0).setCellValue("Option");
		heading.createCell(1).setCellValue("Status");
		for (int r=1; r<options.length+1; r++) {
			XSSFRow row = s.createRow(r);
			row.createCell(0).setCellValue(options[r-1]);
			if (optionStatus.get(r-1)) {
				row.createCell(1).setCellValue("test case passed");
			} else {
				row.createCell(1).setCellValue("test case failed");
			}
		}
		wb.write(file);
		wb.close();
		file.close();
	}
}