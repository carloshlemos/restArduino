/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.alfa.arduino.model;

/**
 *
 * @author carlos-hl
 */

public class ComandoDTO {

    private String ambiente;
    private String utensilio;
    private Integer comando;
    private String macAddress;

    public String getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    public String getUtensilio() {
        return utensilio;
    }

    public void setUtensilio(String utensilio) {
        this.utensilio = utensilio;
    }

    public Integer getComando() {
        return comando;
    }

    public void setComando(Integer comando) {
        this.comando = comando;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

}
