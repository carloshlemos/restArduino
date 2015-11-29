/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
    private void initArduino() {
        arduino = new ControlePorta("/dev/ttyUSB0", 9600); //Linux - porta e taxa de transmissão        
    }
    
    @RequestMapping(value="/teste", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    private @ResponseBody String teste(){
        return "Deu certo";
    }

    @RequestMapping(value = "/enviarComando", method = RequestMethod.POST, 
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    private @ResponseBody
    String enviarComando(@RequestParam(value = "comando") Integer comando) {
        arduino.enviaDados(comando);

        return "Comando executado!";
    }

    @RequestMapping(value = "enviarComandoVoz/", method = RequestMethod.POST, 
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    private ResponseEntity<ComandoDTO> enviarComandoVoz(@RequestBody ComandoDTO comando) {
        try {
            arduino.enviaDados(comando.getComando());
        } catch (Exception e) {
            return new ResponseEntity<ComandoDTO>(comando, HttpStatus.EXPECTATION_FAILED);
        }

        // TODO: adicionar a parte de persistência para auditoria e geração de perfil
        return new ResponseEntity<ComandoDTO>(comando, HttpStatus.OK);
    }

}
