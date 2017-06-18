package br.com.triadworks.bugtracker.modelo;

public class BugPorMes {
	
	Integer mes;
	Integer ano;
	Long total;
	
	
	
	public BugPorMes(Integer mes, Integer ano, Long total) {
		super();
		this.mes = mes;
		this.ano = ano;
		this.total = total;
	}
	public Integer getMes() {
		return mes;
	}
	public void setMes(Integer mes) {
		this.mes = mes;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	
	

}
