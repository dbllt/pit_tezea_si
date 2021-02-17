package tezea.si.model.dto;

import java.io.Serializable;

public class PaginationDTO implements Serializable {

	private Integer size;
	private Integer page;

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

}
