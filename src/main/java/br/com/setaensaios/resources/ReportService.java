package br.com.setaensaios.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.setaensaios.report.FacadeReport;

@Path("report")
public class ReportService {

	@GET
	@Path("/pdf/{id-laudo}/material/{material}/data-inicial/{data-inicial}/data-final/{data-final}/tipo-teste/{tipo-teste}/equipamento/{equipamento}/calibracao/{calibracao}")
	public Response downloadPdfFile(@PathParam("id-laudo") Integer laudo, 
			@PathParam("material") String material, 
			@PathParam("data-inicial") String dataInicial, 
			@PathParam("data-final") String dataFinal, 
			@PathParam("tipo-teste") String tipoTeste,
			@PathParam("equipamento") String equipamento,
			@PathParam("calibracao") String calibracao) {
		
		return Response
				.ok(FacadeReport.getReportPdf(laudo, material, dataInicial, dataFinal, tipoTeste, equipamento, calibracao), MediaType.APPLICATION_OCTET_STREAM)
				.header("content-disposition","attachment; filename = laudos.pdf")
				.build();
	}
}