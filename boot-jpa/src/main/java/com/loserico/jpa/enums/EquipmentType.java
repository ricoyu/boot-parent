package com.loserico.jpa.enums;

/**
 * <p>
 * Copyright: (C), 2023-12-14 15:25
 * <p>
 * <p>
 * Company: Sexy Uncle Inc.
 *
 * @author Rico Yu ricoyu520@gmail.com
 * @version 1.0
 */
public enum EquipmentType {
	
	ELEVATOR("LIFT","提升机"),
	VEHICLE("TAMR","小车");
	
	private final String typeId;
	private final String typeName;
	
	private EquipmentType(String typeId, String typeName){
		this.typeId = typeId;
		this.typeName = typeName;
	}
	
	public String getTypeId() {
		return typeId;
	}
	
	public String getTypeName() {
		return typeName;
	}
}
