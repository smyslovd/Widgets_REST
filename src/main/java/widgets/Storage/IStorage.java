package widgets.Storage;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import widgets.Data.Widget;

public interface IStorage 
{
	Widget insert(Widget widget);
	void delete(UUID id);
	Widget update(Widget widget);
	Widget get(UUID id);
	List<Widget> getAll();
	void clear();
}
