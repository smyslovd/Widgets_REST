package widgets.Data;

import java.util.Date;
import java.util.UUID;

public class Widget implements IWidget {

	private final UUID id;
	private Date dateChange;
	private int xPosition;
	private int yPosition;
	private Integer zIndex;
	private int width;
	private int height;
	
	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Widget(int xPosition, int yPosition, int width, int height, Integer zIndex)
	{
		id = UUID.randomUUID();
		dateChange = new Date();
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.zIndex = zIndex;
		this.width = width;
		this.height = height;
	}
				
	public void setzIndex(int value)
	{
		zIndex = value;
	}
	
	public void zIndexInc()
	{
		zIndex++;
	}
	
	public UUID getId() {
		return id;
	}

	public Date getDateChange() {
		return dateChange;
	}

	public int getxPosition() {
		return xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

	public Integer getzIndex() {
		return zIndex;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public void updateDateChange()
	{
		dateChange = new Date();
	}
		
	@Override
	public boolean equals(Object other)
	{
	    if (other == null) return false;
	    Widget otherWidget = (Widget)other;
	    return xPosition == otherWidget.xPosition &&
	    	   yPosition == otherWidget.yPosition &&
	    	   width == otherWidget.width &&
	    	   height == otherWidget.height;
	}
}
