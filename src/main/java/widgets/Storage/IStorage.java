package widgets.Storage;

import java.util.List;
import java.util.UUID;

import widgets.Data.Widget;

public interface IStorage 
{
	Widget insert(Widget widget);
	void delete(UUID id);
	Widget update(UUID id, int xPosition, int yPosition, int width, int height, int zIndex); 
	Widget get(UUID id);
	List<Widget> getAll();
	void clear();
}
