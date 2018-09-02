package br.com.setaensaios.report;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

public final class FacadeReport {

	private FacadeReport() {

	}

	public static StreamingOutput getReportPdf(Integer laudoId, String material, String dataInicial, 
			String dataFinal, String tipoTeste, String equipamento, String calibracao) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat( "dd/MM/yyyy"); 
		
		//executa o relatório
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("LAUDO_ID", laudoId);
		params.put("MATERIAL", material.equals(" ") ? null : material);
		params.put("DATA_INICIAL", dataInicial.equals(" ") ? null : dateFormat.format(new Date(Long.parseLong(dataFinal))));
		params.put("DATA_FINAL", dataFinal.equals(" ") ? null : dateFormat.format(new Date(Long.parseLong(dataFinal))));
		params.put("TIPO_TESTE", tipoTeste);
		params.put("EQUIPAMENTO", equipamento);
		params.put("CALIBRACAO", calibracao);

		return new StreamingOutput() {
			
			@Override
			public void write(java.io.OutputStream output) throws IOException, WebApplicationException {
				try {
					Path path = BOReport.gerar(getClass().getClassLoader().getResourceAsStream("/laudos.jrxml"), params);
					byte[] data = Files.readAllBytes(path);
					output.write(data);
					output.flush();
				} catch (Exception e) {
					throw new WebApplicationException();
				}
			}
		};
	}
}
