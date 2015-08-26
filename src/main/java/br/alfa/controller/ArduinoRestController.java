package br.alfa.controller;

import br.alfa.arduino.ControlePorta;
import br.alfa.arduino.model.ComandoDTO;
import javax.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author carloshlemos
 */
@Controller
@RequestMapping("/rest/arduino")
public class ArduinoRestController {

    private ControlePorta arduino;

    @PostConstruct
    private void init() {
        arduino = new ControlePorta("/dev/ttyUSB0", 9600); //Linux - porta e taxa de transmissão        
    }

    @RequestMapping(value = "/enviarComando", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    private ResponseEntity<ComandoDTO> enviarComando(@RequestBody ComandoDTO comando) {
        // TODO: adicionar a validação do ambiente e utensílio
        arduino.enviaDados(comando.getComando());
        // TODO: adicionar a parte de persistência para auditoria e geração de perfil
        return new ResponseEntity<ComandoDTO>(comando, HttpStatus.OK);
    }

}
