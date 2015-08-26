package br.alfa.arduino;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author carloshlemos
 */
public class ControlePorta {

    private OutputStream serialOut;
    private int taxa;
    private String portaCOM;

    /**
     * Construtor da classe ControlePorta
     *
     * @param portaCOM - Porta COM que serÃ¡ utilizada para enviar os dados para
     * o arduino
     * @param taxa - Taxa de transferÃªncia da porta serial geralmente Ã© 9600
     */
    public ControlePorta(String portaCOM, int taxa) {
        this.portaCOM = portaCOM;
        this.taxa = taxa;
        this.initialize();
    }

    /**
     * MÃ©doto que verifica se a comunicaÃ§Ã£o com a porta serial estÃ¡ ok
     */
    private void initialize() {
        try {
            //Define uma variÃ¡vel portId do tipo CommPortIdentifier para realizar a comunicaÃ§Ã£o serial
            CommPortIdentifier portId = null;
            try {
                //Tenta verificar se a porta COM informada existe
                portId = CommPortIdentifier.getPortIdentifier(this.portaCOM);
            } catch (NoSuchPortException npe) {
                //Caso a porta COM nÃ£o exista serÃ¡ exibido um erro 
                npe.printStackTrace();
            }
            //Abre a porta COM 
            SerialPort port = (SerialPort) portId.open("ComunicaÃ§Ã£o serial", this.taxa);
            serialOut = port.getOutputStream();
            port.setSerialPortParams(this.taxa, //taxa de transferÃªncia da porta serial 
                    SerialPort.DATABITS_8, //taxa de 10 bits 8 (envio)
                    SerialPort.STOPBITS_1, //taxa de 10 bits 1 (recebimento)
                    SerialPort.PARITY_NONE); //receber e enviar dados
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * MÃ©todo que fecha a comunicaÃ§Ã£o com a porta serial
     */
    public void close() {
        try {
            serialOut.close();
        } catch (IOException e) {
            e.printStackTrace();
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
}
