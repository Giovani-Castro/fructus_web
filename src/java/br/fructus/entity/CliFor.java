
package br.fructus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;




/**
 *
 * @author Giovani
 */



@Entity
public class CliFor extends GenericDomain {
    
    
    
    @Column (nullable = false, length = 70)
    private String nome;
    
    @Column (length = 50)
    private String fantasia;
    
    @Column (nullable = false)
    private Character tipopessoa;
    
    @Column (nullable = false, length = 14)
    private String cpfcnpj;
    
    @Column (nullable = false)
    private Character forcli;
    
    @Column (length = 20)
    private String idestrangeiro;
    
    @Column (length = 60)
    private String logradouro;
    
    @Column (length = 30)
    private String numero;
    
    @Column (length = 60)
    private String complemento;
    
    @Column (length = 60)
    private String bairro;
    
    @ManyToOne
    @JoinColumn ()
    private Municipio municipio;
    
    @Column(length = 8)
    private String cep;
    
    @ManyToOne
    @JoinColumn ()
    private Pais pais;
    
    
    
    @Column (length = 14)
    private String telefone;
    
    @Column (length = 14)
    private String ie;
    
    @Column (length = 14)
    private String iesubstrib;
    
    @Column (length = 15)
    private String imunicipal;
    
    @Column ()
    private Character RegTrib;
    
    
    @Column (length = 60)
    private String email;
    
    @Column (length = 60)
    private String nomecont;
    
    @Column (length = 14)
    private String telcont;
    
    @Column (length = 60)
    private String emailcont;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public Character getTipopessoa() {
        return tipopessoa;
    }

    public void setTipopessoa(Character tipopessoa) {
        this.tipopessoa = tipopessoa;
    }

    public String getCpfcnpj() {
        return cpfcnpj;
    }

    public void setCpfcnpj(String cpfcnpj) {
        this.cpfcnpj = cpfcnpj;
    }

    public Character getForcli() {
        return forcli;
    }

    public void setForcli(Character forcli) {
        this.forcli = forcli;
    }

    public String getIdestrangeiro() {
        return idestrangeiro;
    }

    public void setIdestrangeiro(String idestrangeiro) {
        this.idestrangeiro = idestrangeiro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
        
        
    }

    public String getNomecont() {
        return nomecont;
    }

    public void setNomecont(String nomecont) {
        this.nomecont = nomecont;
    }

    

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getIesubstrib() {
        return iesubstrib;
    }

    public void setIesubstrib(String iesubstrib) {
        this.iesubstrib = iesubstrib;
    }

    public String getImunicipal() {
        return imunicipal;
    }

    public void setImunicipal(String imunicipal) {
        this.imunicipal = imunicipal;
    }

    public Character getRegTrib() {
        return RegTrib;
    }

    public void setRegTrib(Character RegTrib) {
        this.RegTrib = RegTrib;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelcont() {
        return telcont;
    }

    public void setTelcont(String telcont) {
        this.telcont = telcont;
    }

    public String getEmailcont() {
        return emailcont;
    }

    public void setEmailcont(String emailcont) {
        this.emailcont = emailcont;
    }
    
    
}
