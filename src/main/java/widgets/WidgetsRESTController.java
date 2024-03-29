package widgets;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import widgets.DTO.WidgetDTO;
import widgets.Data.Widget;
import widgets.Storage.IStorage;

@RestController
public class WidgetsRESTController 
{
	private IStorage storage;
	
	public WidgetsRESTController(IStorage storage)
	{
		this.storage = storage; 
	}
	
	@GetMapping(path = "/widget/{id}")
	public ResponseEntity<Widget> getWidget(@PathVariable UUID id) 
	{
	    Widget widget = storage.get(id);
	    if (widget == null)
	    {
		    return ResponseEntity.notFound().build();
	    }	    	
	    return ResponseEntity.ok(widget);
	}

	@PostMapping(path = "/widget", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Widget> createWidget(@RequestBody WidgetDTO widget) 
	{
		if (!widget.isDataCorrect())
		{
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		try 
		{
			Widget createdWidget = storage.insert(new Widget(widget.getxPosition(), 
									  widget.getyPosition(), 
									  widget.getWidth(), 
									  widget.getHeight(), 
									  widget.getzIndex()));
			return ResponseEntity.ok(createdWidget);
		}
		catch(Exception ex)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping(path = "/widget/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Widget> updateWidget(@RequestBody WidgetDTO widget, @PathVariable UUID id) 
	{
		try 
		{
	    	Widget updatedWidget = storage.get(id);
	    	if (updatedWidget == null)
	    	{
		        return ResponseEntity.notFound().build();
	    	}
	    	int xPosition = widget.getxPosition() != null ? widget.getxPosition() : updatedWidget.getxPosition();
	    	int yPosition = widget.getyPosition() != null ? widget.getyPosition() : updatedWidget.getyPosition();
	    	int zIndex = widget.getzIndex() != null ? widget.getzIndex() : updatedWidget.getzIndex();
	    	int height = widget.getHeight() != null ? widget.getHeight() : updatedWidget.getHeight();
	    	int width = widget.getWidth() != null ? widget.getWidth() : updatedWidget.getWidth();
	    	
			updatedWidget = storage.update(id, xPosition, yPosition, width, height, zIndex);
			return ResponseEntity.ok(updatedWidget);
		}
		catch(Exception ex)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping(path = "/widget/{id}")
	public ResponseEntity<Void> deleteWidget(@PathVariable UUID id) 
	{
		try 
		{
	    	Widget widget = storage.get(id);
	    	if (widget == null)
	    	{
		        return ResponseEntity.notFound().build();
	    	}
	    	
			storage.delete(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		catch(Exception ex)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping(path = "/widget")
	public ResponseEntity<List<Widget>> getAllWidget() 
	{
		try 
		{			
			return ResponseEntity.ok(storage.getAll());		
		}
		catch(Exception ex)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
