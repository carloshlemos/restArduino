package br.alfa.arduino;

/**
 *
 * @author carloshlemos
 */
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControlePorta implements SerialPortEventListener {

    private SerialPort port;
    private OutputStream serialOut;
    private BufferedReader input;
    private int taxa;
    private String portaCOM;
    private String resposta;

    /**
     * Construtor da classe ControlePorta
     *
     * @param portaCOM - Porta COM que será utilizada para enviar os dados para
     * o arduino
     * @param taxa - Taxa de transferência da porta serial geralmente é 9600
     */
    public ControlePorta(String portaCOM, int taxa) {
        this.portaCOM = portaCOM;
        this.taxa = taxa;
        this.initialize();
    }

    /**
     * Médoto que verifica se a comunicação com a porta serial está ok
     */
    private void initialize() {
        try {
            //Define uma variável portId do tipo CommPortIdentifier para realizar a comunicação serial
            CommPortIdentifier portId = null;
            try {
                //Tenta verificar se a porta COM informada existe
                portId = CommPortIdentifier.getPortIdentifier(this.portaCOM);
            } catch (NoSuchPortException npe) {
                //Caso a porta COM não exista será exibido um erro 
                npe.printStackTrace();
            }
            //Abre a porta COM 
            port = (SerialPort) portId.open("Comunicação serial", this.taxa);
            input = new BufferedReader(new InputStreamReader(port.getInputStream()));
            serialOut = port.getOutputStream();

            port.addEventListener(this);
            port.notifyOnDataAvailable(true);
            port.setSerialPortParams(this.taxa, //taxa de transferência da porta serial 
                    SerialPort.DATABITS_8, //taxa de 10 bits 8 (envio)
                    SerialPort.STOPBITS_1, //taxa de 10 bits 1 (recebimento)
                    SerialPort.PARITY_NONE); //receber e enviar dados
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que fecha a comunicação com a porta serial
     */
    public synchronized void close() {
        if (port != null) {
            port.removeEventListener();
            port.close();
        }
    }

    /**
     * @param opcao - Valor a ser enviado pela porta serial
     */
    public void enviaDados(int opcao) {
        try {
            serialOut.write(opcao);//escreve o valor na porta serial para ser enviado
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public String recebeValor(){
        String valor = null;
        try {
            valor = input.readLine();
        } catch (IOException ex) {
            Logger.getLogger(ControlePorta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return valor;
    }

    /**
     * Handle an event on the serial port. Read the data and print it.
     */
    @Override
    public void serialEvent(SerialPortEvent oEvent) {
        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                this.resposta = input.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getResposta() {
        return resposta;
    }

}
