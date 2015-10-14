package clientetcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;



public class ClienteTCP implements Runnable {
	
	private Socket cliente;
	String NCliente;
	//private int Porta;
	public void client(){
		try{
			this.cliente = new Socket("localhost",59155);
		}
		catch(Exception e){
			System.out.println("Erro:"+ e.getMessage());
		}
	}
	
	public void main(ClienteTCP c1){
		//ClienteTCP c1 = new ClienteTCP();
		c1.client();
		c1.online();
		Thread cliente  = new Thread(c1);
		//cliente.start();
	}
	
	public void online(){
		String Nome,NC;

	     ArrayList<ArrayList<String>> contatos = new ArrayList<ArrayList<String>>();
	     ArrayList<String> cont = new ArrayList<String>();
	     ArrayList<String> sta = new ArrayList<String>();
		try{
			//System.out.print("Digite seu Nome:");
			//BufferedReader sc1 = new BufferedReader(new InputStreamReader(System.in));
           Socket client = this.cliente;
           
           DataOutputStream ParaServer = new DataOutputStream(client.getOutputStream());
           BufferedReader DoServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
  
           Nome = this.NCliente;
           ParaServer.writeBytes(Nome + "\n");
           ParaServer.flush();
           
           while(!(NC = DoServer.readLine()).equals("Contatos")){
        	   cont.add(NC);
           }
           while(!(NC = DoServer.readLine()).equals("Status")){
        	   sta.add(NC);
           }
           contatos.add(cont);
           contatos.add(sta);
          
           for(int y=0;y<contatos.get(1).size();y++){
        	   if(contatos.get(1).get(y).equals("Online"))System.out.println("\nContato Online: " + contatos.get(0).get(y));
        	}
           //sc1.close();
           //client.close();  
			
		}
		catch(Exception e){
			System.out.println("Erro:"+ e.getMessage());
		}
	}
	
	
	public void run() {
		
		try{
		 	System.out.print("Digite a Porta:");
			BufferedReader sc1 = new BufferedReader(new InputStreamReader(System.in));
			String Porta = sc1.readLine();
			String line = "";
			int p1 = Integer.parseInt(Porta);
			Socket c2 = new Socket("localhost",p1);
			DataOutputStream ParaServer = new DataOutputStream(c2.getOutputStream());
	        BufferedReader DoServer = new BufferedReader(new InputStreamReader(c2.getInputStream()));
	        while(true){
	        	if(line!=null){
	        		line=DoServer.readLine();
		        	System.out.println(line);
	        	}
	        	else{
	        		BufferedReader Digito1 = new BufferedReader(new InputStreamReader(System.in));
	        		String Digito = Digito1.toString();
	        		ParaServer.writeBytes(Digito);
	        		ParaServer.flush();
	        	}
	        }
		}
		catch(Exception e){
			System.out.println("Erro:"+ e.getMessage());
		}
	}

}