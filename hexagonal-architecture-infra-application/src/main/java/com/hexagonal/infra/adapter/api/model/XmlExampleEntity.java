
package com.hexagonal.infra.adapter.api.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.google.common.base.MoreObjects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "id", "name" })
@XmlRootElement(name = "entity")
public class XmlExampleEntity implements Serializable {

	@XmlTransient
	private static final long serialVersionUID = 1328776989450853491L;

	@XmlElement
	private Integer id;

	@XmlElement
	private String name;

	public XmlExampleEntity() {
		super();
	}

	@Override
	public final boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		final XmlExampleEntity other = (XmlExampleEntity) obj;
		return Objects.equals(id, other.id);
	}

	public final Integer getId() {
		return id;
	}

	public final String getName() {
		return name;
	}

	public final int hashCode() {
		return Objects.hash(id);
	}

	public final void setId(final Integer identifier) {
		id = checkNotNull(identifier, "Received a null pointer as identifier");
	}

	public final void setName(final String name) {
		this.name = checkNotNull(name, "Received a null pointer as name");
	}

	@Override
	public final String toString() {
		return MoreObjects.toStringHelper(this).add("entityId", id).toString();
	}

}
