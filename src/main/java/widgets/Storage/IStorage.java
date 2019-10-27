package widgets.Storage;

import java.util.LinkedList;
import java.util.UUID;

import widgets.Data.Widget;

public interface IStorage 
{
	Widget insert(Widget widget);
	void delete(UUID id);
	Widget update(Widget widget);
	Widget get(UUID id);
	LinkedList<Widget> getAll();
	void clear();
}
