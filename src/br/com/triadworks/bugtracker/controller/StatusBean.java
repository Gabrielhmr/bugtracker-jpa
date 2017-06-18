package br.com.triadworks.bugtracker.controller;

import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.triadworks.bugtracker.modelo.Status;

@ManagedBean
public class StatusBean {

	public List<Status> getTodos() {
		return Arrays.asList(Status.values());
	}
	
}
