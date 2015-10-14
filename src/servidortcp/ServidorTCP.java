package servidortcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ServidorTCP implements Runnable {
	//private static Thread threadServer;
	private ServerSocket servidor;
	
	public void servidor(){
		try{
			this.servidor = criaSocket();
		}
		catch(Exception e){
			System.out.println("Erro:"+ e.getMessage());
		}
	   
		System.out.println("=== Conexoes ===");
	}
	
	public ArrayList<ArrayList<String>> statusInicial(){
		ArrayList<ArrayList<String>> Online = new ArrayList<ArrayList<String>>();
	    ArrayList<String> Usu = leClientes();
	    ArrayList<String> Aux = null;
	    for(int i = 0;i<Usu.size();i++){
	    	Aux = new ArrayList<String>();
	    	Aux.add(Usu.get(i));
	    	Online.add(Aux);
	    	Online.get(i).add("Offline");
	    	Aux=null;
	    }
	    
	    /*for(int i = 0;i<Online.size();i++){
	    	for(int y = 0;y<Online.get(i).size();y++){
	    		System.out.print(Online.get(i).get(y)+" ");
	    	}
	    	System.out.println();
	    }*/
		return Online;
	}
	
	public ArrayList<ArrayList<String>> status(ArrayList<ArrayList<String>> Online, String Pessoa,int Porta,int s){
		if(s == 1){
			for(int i = 0;i<Online.size();i++){
            	
            	if((Online.get(i).get(0).equals(Pessoa))){
            		Online.get(i).remove(1);
            		Online.get(i).add("Online");
            		Online.get(i).add(Integer.toString(Porta));
            	}
            	
            }
            
            
            for(int i = 0;i<Online.size();i++){
    	    	for(int y = 0;y<Online.get(i).size();y++){
    	    		System.out.print(Online.get(i).get(y)+" ");
    	    	}
    	    	System.out.println();
    	    }
            
            return Online;
		}
		return null;
	}


	public void run() {
		
		String Pessoa;
	    ArrayList<ArrayList<String>> Contatos =leContatos();
	    ArrayList<ArrayList<String>> Online = statusInicial();
	    
			try {  
		        while(true) {    
		     	   	Socket conexao = this.servidor.accept();
		     	   
		            BufferedReader DoCliente = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
		            DataOutputStream ParaCliente = new DataOutputStream(conexao.getOutputStream());
		 
		            Pessoa = DoCliente.readLine();
		            
		            System.out.println("Cliente "+Pessoa+" conectado: " + conexao.getInetAddress().getHostAddress()+" Porta:"+
 	   						conexao.getPort()+" "+new Date());
		            Online = status(Online,Pessoa,conexao.getPort(),1);
		            
		            
		            ArrayList<String> Co = retornaContatos(Pessoa,Contatos);
		            //for(int i = 0;i < Co.size();i++)System.out.println(Co.get(i));

		            /*for(int i = 0;i < Co.size();i++){
		            	
		            	ParaCliente.writeBytes(Co.get(i)+ "\n");
		            	ParaCliente.flush();
		            }*/
		           
		            for(int y = 0;y<Co.size();y++){
		            	for(int i = 0;i<Online.size();i++){
		            		if(Co.get(y).equals(Online.get(i).get(0))){
		            			ParaCliente.writeBytes(Co.get(y)+"\n");
		            			//ParaCliente.writeBytes(Online.get(i).get(1)+"\n");
				            	ParaCliente.flush();
		            		}
		            	}
		            }
		            
		            ParaCliente.writeBytes("Contatos"+"\n");
		            ParaCliente.flush();
		            for(int y = 0;y<Co.size();y++){
		            	for(int i = 0;i<Online.size();i++){
		            		if(Co.get(y).equals(Online.get(i).get(0))){
		            			//ParaCliente.writeBytes(Co.get(y)+"\n");
		            			ParaCliente.writeBytes(Online.get(i).get(1)+"\n");
				            	ParaCliente.flush();
		            		}
		            	}
		            }
		            ParaCliente.writeBytes("Status"+"\n");
		            ParaCliente.flush();
		            //ParaCliente.writeBytes(Pessoa + "\n");

		        } 
		   
		    } catch (Exception e) {
		 	   System.out.println("Erro:"+ e.getMessage());
		    	}
		
	}
	
	public static ArrayList<String> leClientes(){
	 	   ArrayList<String> Clientes = new ArrayList<String>();
	 	   try{
	 		   Scanner le = new Scanner(new FileReader("C:\\Users\\giova\\Desktop\\Redes\\Testes\\Clientes.txt"));
	 		   while (le.hasNext()) {
	 			   Clientes.add(le.next());
	 			 }
	 		   le.close();
	 	   }
	 	   catch(Exception e){
	 		   System.out.println("Erro:"+ e.getMessage()); 
	 	   }
	 	   //for(int i = 0;i<Clientes.size();i++)System.out.println(Clientes.get(i));
	 	   return Clientes;
	}
	
	
	public static ArrayList<ArrayList<String>> leContatos(){
	 	   ArrayList<ArrayList<String>> Contatos = new ArrayList<ArrayList<String>>();
	 	   ArrayList<String> ContatoC = null;
	 	   try{
	 		   Scanner le = new Scanner(new FileReader("C:\\Users\\giova\\Desktop\\Redes\\Testes\\Contatos.txt"));
	 		   while (le.hasNext()) {
	 			   ContatoC = new ArrayList<String>();
	 			   String[] cont = le.next().split(";");
	 			   for(int i = 0;i<cont.length;i++)ContatoC.add(cont[i]);
	 			   //for(int i = 0;i<ContatoC.size();i++)System.out.print(ContatoC.get(i)+" ");
	 			   //System.out.println();
	 			   Contatos.add(ContatoC);
	 			   ContatoC = null;
	 			 }	   
	 		   le.close();
	 	   }
	 	   catch(Exception e){
	 		   System.out.println("Erro:"+ e.getMessage()); 
	 	   }
	 	   /*for(int i = 0;i<Contatos.size();i++){
	 		   for(int y = 0;y<Contatos.get(i).size();y++)System.out.print(Contatos.get(i).get(y)+" ");
	 		   System.out.println();
	 	   }*/
	 	   return Contatos;
	    }
	
	public static ArrayList<String> retornaContatos(String Pessoa,ArrayList<ArrayList<String>> Contatos){
		ArrayList<String> retorno = new ArrayList<String>();
	  	for(int i = 0; i<Contatos.size();i++){
	  	  if((Contatos.get(i).get(0)).equals(Pessoa)){ 
	  		  for(int y = 0;y<Contatos.get(i).size();y++){
	  			  retorno.add(Contatos.get(i).get(y));
	  		  }
	  	  }
	  	 }
	  	 retorno.remove(0);
	  	 return retorno;
	 }
	
	 public static ServerSocket criaSocket(){
		   try{
			   ServerSocket servidor = new ServerSocket(59155);
			   return servidor;
		   }
		   catch(Exception e){
			   System.out.println("Erro:"+ e.getMessage()); 
		   }
		   return null;
	   }
	
	public static void main(String[] args){
		ArrayList<String> clientes= leClientes();
		ServidorTCP servers = new ServidorTCP();
		servers.servidor();
		Thread serve = new Thread(servers);
		serve.start();

	}
}
