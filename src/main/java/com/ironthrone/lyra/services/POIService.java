package com.ironthrone.lyra.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ironthrone.lyra.pojo.AlumnoPOJO;
import com.ironthrone.lyra.pojo.GradoPOJO;
import com.ironthrone.lyra.pojo.MateriaPOJO;
import com.ironthrone.lyra.pojo.UsuarioPOJO;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import org.springframework.stereotype.Service;

@Service
public class POIService implements POIServiceInterface {

	// Get the file we're working with.
	private File myXSLS;
	
	//Get the workbook instance for XLS file 
	private XSSFWorkbook workbook;
	
	//The sheets contained within the xml
	
	private XSSFSheet adminSheet;
	
	private XSSFSheet profesorSheet;
	
	private XSSFSheet encargadoSheet;
	
	private XSSFSheet materiaSheet;
	
	private XSSFSheet gradoSheet;
	
	private XSSFSheet alumnoSheet;
	 
	//Get iterator to all cells of current row
	private Iterator<Cell> cellIterator;
	
	//creating formatter using the default locale
	private DataFormatter formatter = new DataFormatter(); 
	
	//Final names of the sheets in the xlsl, the system will check if they match before doing anything.
	
	final String _admin     = 	"1-Admin";
	
	final String _profe     = 	"2-Profesor";
	
	final String _encargado = 	"3-Encargado";
	
	final String _student   = 	"4-Estudiante";
	
	final String _materia   = 	"5-Materia";
	
	final String _grado     = 	"6-Grado";
	
	// Ignore the first row, for it a header. Based on 0,1,2 etc...
	int FIRST_ROW_TO_GET = 2; 
	
	//Most services uses -1 to determine it is a fresh entry.
	int CREATE_ID = -1;
	
	//The value of each role in the database
	
	final String ADMIN_ROLE = "2";
	
	final String PROFESOR_ROLE = "3";
	
	final String PARENT_ROLE = "4";

	//The value for the Gender option in student, because Wendy couldn't bother to use F and M...
	
	final String HOMBRE = "M";
	
	final String MUJER = "F";
	
	@Override
	public void setEnviroment(String _file) throws IllegalStateException, IOException, InvalidFormatException {
		

			myXSLS = new File (_file);
	     //   System.out.println("My xls path is: " + myXSLS.getAbsolutePath());

	        workbook = new XSSFWorkbook(myXSLS.getAbsolutePath());

	     //   System.out.println("Numero de Hojas: " + workbook.getNumberOfSheets());
	        
	        
	        //Match sheet with the sheet name in case the xlsx has been compromised.
	        
	        for (Sheet sheet : workbook){
	        	
	        	if (sheet.getSheetName().trim().equals(_admin)){
	        		adminSheet = (XSSFSheet)sheet;
	        	//	System.out.println("Leyendo la hoja: " + adminSheet.getSheetName());
	        	}
	        	
	        	if (sheet.getSheetName().trim().equals(_profe)){
	        		profesorSheet = (XSSFSheet)sheet;
	        	//	System.out.println("Leyendo la hoja: " + profesorSheet.getSheetName());
	        	}
	        	
	        	if (sheet.getSheetName().trim().equals(_encargado)){
	        		encargadoSheet = (XSSFSheet)sheet;
	        	//	System.out.println("Leyendo la hoja: " +  encargadoSheet.getSheetName());
	        	}
	        	
	        	if (sheet.getSheetName().trim().equals(_materia)){
	        		materiaSheet = (XSSFSheet)sheet;
	        	//	System.out.println("Leyendo la hoja: " +  materiaSheet.getSheetName());
	        	} 
	        	
	        	if (sheet.getSheetName().trim().equals(_grado)){
	        		gradoSheet = (XSSFSheet)sheet;
	        	//	System.out.println("Leyendo la hoja: " + gradoSheet.getSheetName());
	        	} 

	        	if (sheet.getSheetName().trim().equals(_student)){
	        		alumnoSheet = (XSSFSheet)sheet;
	        	//	System.out.println("Leyendo la hoja: " + alumnoSheet.getSheetName());
	        	}
	        }
	       
	        
	        
	}

	/**
	 * Metodo que llena una lista de usuarios con los datos extraidos del excel.
	 * @author jeanpaul
	 * @param Lista de usuarios a llenar
	 */
	@Override
	public List<UsuarioPOJO> getUsers(List<UsuarioPOJO> users) {
		
		UsuarioPOJO newUser = new UsuarioPOJO();
		//int i = 0;
				
		// Verify that the sheet is populated, if not skip the search and save memory
		if (isEmpty(adminSheet.getRow(FIRST_ROW_TO_GET))){
			
			//Created a role list and assigns the user's role based on the sheet being read.
			List<String> rols = new ArrayList<String>();
			String rol = ADMIN_ROLE;
			rols.add(rol);

			Iterator<Row> adminRowIterator = adminSheet.iterator();
			
			    while(adminRowIterator.hasNext()){
			    	
			        Row row = adminRowIterator.next();
			        
			        if(row.getRowNum() == 0){
			        	   continue; //just skip the rows if row number is 0
			       }
			        
			        //Call method that reads from the excel and return a userPojo
			        newUser = getUser(row);
			        
			        //If the user is null don't add it to the list, otherwise go.
				        if(newUser.getEmail() != null){
				        	
					        newUser.setIdUsuario(CREATE_ID);
					        newUser.setIdRoles(rols);
					        users.add(newUser);

			        }
			 }			
		}
	
		//Ditto
		if (isEmpty(profesorSheet.getRow(FIRST_ROW_TO_GET))){
			
			List<String> rols = new ArrayList<String>();
			String rol = PROFESOR_ROLE;
			rols.add(rol);
			Iterator<Row> profeRowIterator = profesorSheet.iterator();

			    while(profeRowIterator.hasNext()){
			    	
			        Row row = profeRowIterator.next();
			        
			        if(row.getRowNum() == 0){
			        	   continue; //just skip the rows if row number is 0
			       }
			        
			        newUser = getUser(row);
			        
				        if(newUser.getEmail() != null){
				        	
					        newUser.setIdUsuario(CREATE_ID);
					        newUser.setIdRoles(rols);
					        users.add(newUser);

			        }
			 }			
		}
		
		//Ditto
		if (isEmpty(encargadoSheet.getRow(FIRST_ROW_TO_GET))){
			
			List<String> rols = new ArrayList<String>();
			String rol = PARENT_ROLE;
			rols.add(rol);
			Iterator<Row> encarRowIterator = encargadoSheet.iterator();
	
			
			    while(encarRowIterator.hasNext()){
			    	
			        Row row = encarRowIterator.next();
			        
			        if(row.getRowNum() == 0){
			        	   continue; //just skip the rows if row number is 0
			       }
			        
			        newUser = getUser(row);
			        
				        if(newUser.getEmail() != null){
				        	
					        newUser.setIdUsuario(CREATE_ID);
					        newUser.setIdRoles(rols);
					        users.add(newUser);

			        }
			 }			
		}
 			
//		while (users.iterator().hasNext()) {
//	        System.out.println(users.get(i).toString());  
//	        i = i+1;
//		}
	
		
		return users;
	}

	
	
	
	/**
	 * Metodo que lee de la hoja de excel de usuarios [Admin, Profesor, Encargado] y
	 * construye un objeto usuario a partir de este.
	 * @param row
	 * @return usuarioPOJO
	 */
	private UsuarioPOJO getUser(Row row) {
		
		UsuarioPOJO u = new UsuarioPOJO();
		Cell cell;
		String cellValue = ""; 

		cellIterator = row.cellIterator();  
		     
		while (cellIterator.hasNext()) {  
			
			cellValue = ""; 
			cell = cellIterator.next(); 

            switch (cell.getCellType()) {
            
	            case Cell.CELL_TYPE_STRING:
		            	cellValue = cell.getRichStringCellValue().getString();
		             //   System.out.println(cell.getRichStringCellValue().getString() + "\t\t");
	                break;
	            case Cell.CELL_TYPE_NUMERIC:
	            	 	cellValue = formatter.formatCellValue(cell);
	                 //   System.out.println(cellValue + "\t\t");
	                break;
	            default:

            }
            
            
	       switch (cell.getColumnIndex() + 1) {
	            
	            case 1:
	            		u.setCedula(cellValue);
	                break;
	                
	            case 2:
	            		u.setNombre(cellValue);
	                break;
	                
	            case 3:
	        	 		u.setApellido(cellValue);
	                break;
	            
	            case 4:
	            		u.setEmail(cellValue); 
	                break;
	            
	            case 5:
	            		u.setTelefono(cellValue);
	                break;
	        
	            case 6:
	            		u.setMovil(cellValue);
                break;
                
	            default:
	                
	        }
	 			
	    }
		
	//	System.out.println(u.toString());     
            
		return u;
	}


	/**
	 * Metodo que llena una lista de grados con los datos extraidos del excel.
	 * @author jeanpaul
	 * @param Lista de grados a llenar
	 */
	@Override
	public List<GradoPOJO> getGrados(List<GradoPOJO> grados) {
	
		GradoPOJO newGrado = new GradoPOJO();

		if (isEmpty(gradoSheet.getRow(FIRST_ROW_TO_GET))){
			
			Iterator<Row> gradoRowIterator = gradoSheet.iterator();
			
			    while(gradoRowIterator.hasNext()){
			    	
			        Row row = gradoRowIterator.next();
			        
			        if(row.getRowNum() == 0){
			        	   continue;
			        }	
			        
			        newGrado = getGrado(row);
			        
				      if(newGrado.getNombre() != null){
				    	  newGrado.setIdGrado(-1);
					      grados.add(newGrado);

				      }
				 }			
		  }
		
		return grados;
	}

	/**
	 * Metodo que lee de la hoja de excel de grados y
	 * construye un objeto grado a partir de este.
	 * @param row
	 * @return gradoPOJO
	 */
	
	private GradoPOJO getGrado(Row row) {
		
		GradoPOJO g = new GradoPOJO();
		Cell cell;
		String cellValue = ""; 

		cellIterator = row.cellIterator();  
		     
		while (cellIterator.hasNext()) {  
			
			cellValue = ""; 
			cell = cellIterator.next(); 

            switch (cell.getCellType()) {
            
	            case Cell.CELL_TYPE_STRING:
		            	cellValue = cell.getRichStringCellValue().getString();
	                break;
	            case Cell.CELL_TYPE_NUMERIC:
	            	 	cellValue = formatter.formatCellValue(cell);
	                break;
	            default:
	                System.out.println();
            }
            
            
	       switch (cell.getColumnIndex() + 1) {
	            
	            case 1:
	            		g.setNombre(cellValue);
	                break;
	                
	            case 2:
	            		g.setDescripcion(cellValue);
	                break;
                
	            default:
	                
	        }
	 			
	    }
		
//		System.out.println("Grado");
//		System.out.println(g.getNombre());
//		System.out.println(g.getDescripcion());          
		return g;
	
	}

	/**
	 * Metodo que llena una lista de materias con los datos extraidos del excel.
	 * @author jeanpaul
	 * @param Lista de materias a llenar
	 */
	@Override
	public List<MateriaPOJO> getMaterias(List<MateriaPOJO> materias) {
		
		MateriaPOJO newMateria = new MateriaPOJO();

		if (isEmpty(materiaSheet.getRow(FIRST_ROW_TO_GET))){
			
			Iterator<Row> materiaRowIterator = materiaSheet.iterator();
			
			    while(materiaRowIterator.hasNext()){
			    	
			        Row row = materiaRowIterator.next();
			        
			        if(row.getRowNum() == 0){
			        	   continue;
			        }	
			        
			        newMateria = getMateria(row);
			        
				      if(newMateria.getNombre() != null){
				    	  newMateria.setIdMateria(-1);
				    	  materias.add(newMateria);

				      }
				 }			
		  }
		
		return materias;
	}

	
	/**
	 * Metodo que lee de la hoja de excel de materias y
	 * construye un objeto materia a partir de este.
	 * @param row
	 * @return MateriaPOJO
	 */
	private MateriaPOJO getMateria(Row row) {
		
		MateriaPOJO m = new MateriaPOJO();
		Cell cell;
		String cellValue = ""; 

		cellIterator = row.cellIterator();  
		     
		while (cellIterator.hasNext()) {  
			
			cellValue = ""; 
			cell = cellIterator.next(); 

            switch (cell.getCellType()) {
            
	            case Cell.CELL_TYPE_STRING:
		            	cellValue = cell.getRichStringCellValue().getString();
	                break;
	            case Cell.CELL_TYPE_NUMERIC:
	            	 	cellValue = formatter.formatCellValue(cell);
	                break;
	            default:
            }
            
            
	       switch (cell.getColumnIndex() + 1) {
	            
	            case 1:
	            		m.setNombre(cellValue);
	                break;
                
	            default:           
	        }			
	    }
      
//		System.out.println("Materia");
//		System.out.println(m.getNombre());
		
		return m;
	}

	/**
	 * Metodo que llena una lista de alumnos con los datos extraidos del excel.
	 * @author jeanpaul
	 * @param Lista de alumnos a llenar
	 */
	@Override
	public List<AlumnoPOJO> getAlumnos(List<AlumnoPOJO> students) {
		
		AlumnoPOJO newStudent = new AlumnoPOJO();

		if (isEmpty(alumnoSheet.getRow(FIRST_ROW_TO_GET))){
			
			Iterator<Row> alumnoRowIterator = alumnoSheet.iterator();
			
			    while(alumnoRowIterator.hasNext()){
			    	
			        Row row = alumnoRowIterator.next();
			        
			        if(row.getRowNum() == 0){
			        	   continue;
			        }	
			        
			        newStudent = getAlumno(row);
			        
				      if(newStudent.getCedula() != null){
				    	  newStudent.setIdAlumno(-1);
				    	  students.add(newStudent);

				      }
				 }			
		  }
		
		return students;
	}
	
	/**
	 * Metodo que lee de la hoja de excel de alumnos y
	 * construye un objeto alumnos a partir de este.
	 * @param row
	 * @return AlumnoPOJO
	 */
	
	private AlumnoPOJO getAlumno(Row row) {
		
		AlumnoPOJO a = new AlumnoPOJO();
		Cell cell;
		String cellValue = ""; 

		cellIterator = row.cellIterator();  
		     
		while (cellIterator.hasNext()) {  
			
			cellValue = ""; 
			cell = cellIterator.next(); 

            switch (cell.getCellType()) {
            
	            case Cell.CELL_TYPE_STRING:
		            	cellValue = cell.getRichStringCellValue().getString();
	                break;
	            case Cell.CELL_TYPE_NUMERIC:
	            	 	cellValue = formatter.formatCellValue(cell);
	                break;
	            default:
	                System.out.println();
            }
            
            
	       switch (cell.getColumnIndex() + 1) {
	            
	            case 1:
	            		a.setCedula(cellValue);
	                break;

	            case 2:
            			a.setNombre(cellValue);
            		break;
                
	            case 3:
            			a.setApellido1(cellValue);
            		break;
                
	            case 4:
            			a.setApellido2(cellValue);
            		break;
                
	            case 5:
	            	if(cellValue.equals("Masculino")){
	            		a.setGenero(HOMBRE);
	            	}else{
	            		a.setGenero(MUJER);
	            	}
            			
	            	break;
                
	            case 6:
	            		UsuarioPOJO u = new UsuarioPOJO();
	            		List<UsuarioPOJO> lista = new ArrayList<UsuarioPOJO>();
	            		u.setCedula(cellValue);
	            		lista.add(u);
            			a.setUsuarios(lista);
            			
            		break;
                
//	            case 7:
//            			a.setNombre(cellValue);
//                break;
                
	            default:           
	        }			
	    }
      
//		System.out.println("Alumno");
//		System.out.println(a.getCedula());
//		System.out.println(a.getNombre());
//		System.out.println(a.getApellido1());
//		System.out.println(a.getApellido2());
//		System.out.println(a.getGenero());
//		System.out.println(a.getUsuarios());

		
		return a;
	}

	public boolean isEmpty(Row r){
		 boolean hasData = true;
		 
		 if (r == null) {
		    // Row has never been used
		    hasData = false;
		 } else {
		    // Check to see if all cells in the row are blank (empty)
		    hasData = false;
		    for (Cell c : r) {
		       if (c.getCellType() != Cell.CELL_TYPE_BLANK) {
		         hasData = true;
		         break;
		       }
		    }
		 }	 
		 return hasData;
	}

}
