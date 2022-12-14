package com.belval.adocaoanimais.dto;

import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.belval.adocaoanimais.enums.Especie;
import com.belval.adocaoanimais.enums.Porte;
import com.belval.adocaoanimais.model.Animal;

public class RequisicaoFormAnimal {
    @NotBlank
    @NotNull
    private String nome;
    private Long raca;
    private Long cor;
    private Porte porte;
    private Especie especie;

    private int sexo;
    private int vacina;

    private Date nascimento;
    private boolean disponivel;

    private String resumo;
    private String observacao;

    public Animal toAnimal() {
        Animal animal = new Animal();
        animal.setNome(this.nome);
        animal.setRaca(this.raca);
        animal.setCor(this.cor);
        animal.setPorte(this.porte);
        animal.setEspecie(this.especie);
        animal.setSexo(this.sexo);
        animal.setVacina(this.vacina);
        animal.setNascimento(this.nascimento);
        animal.setDisponivel(disponivel);
        animal.setObservacao(observacao);
        animal.setResumo(resumo);
        return animal;
    }

    public Animal toAnimal(Animal animal) {
        animal.setNome(this.nome);
        animal.setRaca(this.raca);
        animal.setCor(this.cor);
        animal.setPorte(this.porte);
        animal.setEspecie(this.especie);
        animal.setSexo(this.sexo);
        animal.setVacina(this.vacina);
        animal.setNascimento(this.nascimento);
        animal.setDisponivel(this.disponivel);
        animal.setObservacao(observacao);
        animal.setResumo(resumo);
        return animal;
    }

    public void fromAnimal(Animal animal) {
        this.nome = animal.getNome();
        this.raca = animal.getRaca();
        this.cor = animal.getCor();
        this.porte = animal.getPorte();
        this.especie = animal.getEspecie();
        this.sexo = animal.getSexo();
        this.vacina = animal.getVacina();
        this.nascimento = animal.getNascimento();
        this.disponivel=animal.isDisponivel();
        this.observacao=animal.getObservacao();
        this.resumo=animal.getResumo();
    }

    @Override
    public String toString() {
        return "RequisicaoFormAnimal [nome=" + nome + ", raca=" + raca + ", cor=" + cor + ", porte=" + porte
                + ", especie=" + especie + ", sexo=" + sexo + ", vacina=" + vacina + ", nascimento=" + nascimento + "]";
    }

    public Animal toAnimalCheck() {
        Animal animal = new Animal();
        animal.setDisponivel(this.disponivel);
        return animal;
    }

    public Animal toAnimalCheck(Animal animal) {
        animal.setDisponivel(this.disponivel);
        return animal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getRaca() {
        return raca;
    }

    public void setRaca(Long raca) {
        this.raca = raca;
    }

    public Long getCor() {
        return cor;
    }

    public void setCor(Long cor) {
        this.cor = cor;
    }

    public Porte getPorte() {
        return porte;
    }

    public void setPorte(Porte porte) {
        this.porte = porte;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public int getVacina() {
        return vacina;
    }

    public void setVacina(int vacina) {
        this.vacina = vacina;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

   

}
