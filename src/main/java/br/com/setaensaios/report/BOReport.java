package br.com.setaensaios.report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public final class BOReport {

	private static final String url = "jdbc:postgresql://localhost:5432/teste";
	private static final String driver = "org.postgresql.Driver";
	private static final String login = "postgres";
	private static final String pwd = "system";
	
	private BOReport() {
		
	}
	
	public static Path gerar( InputStream layout, Map<String, Object> params ) throws JRException , SQLException, ClassNotFoundException {
		//gerando o jasper design
		JasperDesign desenho = JRXmlLoader.load( layout );

		//compila o relatório
		JasperReport relatorio = JasperCompileManager.compileReport( desenho );

		//estabelece conexão
		Class.forName( driver );
		Connection con = DriverManager.getConnection( url , login , pwd );
		Statement stm = con.createStatement();
		String query = "select 1 = 1";
		ResultSet rs = stm.executeQuery( query );

		//implementação da interface JRDataSource para DataSource ResultSet
		JRResultSetDataSource jrRS = new JRResultSetDataSource( rs ); 

		//executa o relatório
		params.put("REPORT_CONNECTION", con);
		JasperPrint print = JasperFillManager.fillReport( relatorio , params, jrRS );

		File pdf;
		try {
			pdf = File.createTempFile("laudos.", ".pdf");
			JasperExportManager.exportReportToPdfStream(print, new FileOutputStream(pdf));
			
			return pdf.toPath();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
