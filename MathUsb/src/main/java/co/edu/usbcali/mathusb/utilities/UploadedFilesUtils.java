//package com.vortexbird.gluon.utilities;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.primefaces.model.UploadedFile;
//
//import com.vortexbird.gluon.modelo.dto.GluoArchivoSoporteDTO;
//
//public class UploadedFilesUtils {
//
//	public static GluoArchivoSoporteDTO uploadedFileToGluoArchivoSoporteDTO (UploadedFile uploadedFile) throws Exception{
//		
//		if (uploadedFile == null){
//			return null;
//		}
//		
//		GluoArchivoSoporteDTO gluoArchivoSoporteDTO = new GluoArchivoSoporteDTO();
//		
//		byte[] bytes = new byte[(int)uploadedFile.getSize()];
//		uploadedFile.getInputstream().read(bytes);
//		
//		gluoArchivoSoporteDTO.setArchivo(bytes);
//		gluoArchivoSoporteDTO.setDescripcion(uploadedFile.getFileName());
//		gluoArchivoSoporteDTO.setMimeType(uploadedFile.getContentType());
//		
//		return gluoArchivoSoporteDTO;
//		
//	}
//	
//	public static List<GluoArchivoSoporteDTO> uploadedFilesToGluoArchivoSoporteDTOs (List<UploadedFile> uploadedFiles) throws Exception{
//		
//		List<GluoArchivoSoporteDTO> lista = new ArrayList<GluoArchivoSoporteDTO>();
//		
//		if (uploadedFiles == null){
//			return null;
//		}
//		
//		for(UploadedFile uploadedFile : uploadedFiles){
//			GluoArchivoSoporteDTO gluoArchivoSoporteDTO = new GluoArchivoSoporteDTO();
//			
//			byte[] bytes = new byte[(int)uploadedFile.getSize()];
//			uploadedFile.getInputstream().read(bytes);
//			
//			gluoArchivoSoporteDTO.setArchivo(bytes);
//			gluoArchivoSoporteDTO.setDescripcion(uploadedFile.getFileName());
//			gluoArchivoSoporteDTO.setMimeType(uploadedFile.getContentType());
//			
//			lista.add(gluoArchivoSoporteDTO);
//		}
//		
//		return lista;
//		
//	}
//	
//}
