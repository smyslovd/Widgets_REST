package widgets.DTO;

public class WidgetDTO 
{
	private Integer xPosition;
	private Integer yPosition;
	private Integer zIndex;
	private Integer width;
	private Integer height;
	
	public WidgetDTO(Integer xPosition, Integer yPosition, Integer zIndex, Integer width, Integer height)
	{
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.zIndex = zIndex;
		this.width = width;
		this.height = height;
	}

	public Boolean isDataCorrect()
	{
		return xPosition != null && yPosition != null && width != null && height != null;
	}
		
	public Integer getxPosition() {
		return xPosition;
	}
	
	public Integer getyPosition() {
		return yPosition;
	}
	
	public Integer getzIndex() {
		return zIndex;
	}
	
	public Integer getWidth() {
		return width;
	}
	
	public Integer getHeight() {
		return height;
	}
}
