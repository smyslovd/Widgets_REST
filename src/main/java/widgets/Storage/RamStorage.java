package widgets.Storage;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import widgets.Data.Widget;

public class RamStorage implements IStorage 
{
	private LinkedList<Widget> widgets = new LinkedList<Widget>();
	
	@Override
	public Widget insert(Widget widget) 
	{
		synchronized(widgets)
		{
			return insertWithSort(widget);
		}
	}
	
	@Override
	public void delete(UUID id) 
	{
		synchronized(widget.getsyncRoot())
		{
			widgets.removeIf(w -> w.getId().equals(id));
		}
	}

	@Override
	public Widget update(Widget widget) 
	{
		synchronized(widget.getsyncRoot())
		{
			widgets.removeIf(w -> w.getId().equals(widget.getId()));
			return insertWithSort(widget);				
		}
	}

	@Override
	public Widget get(UUID id) 
	{
		synchronized(widgets)
		{
			return widgets.stream().filter(w -> w.getId().equals(id)).findFirst().orElse(null);
		}
	}

	@Override
	public List<Widget> getAll() 
	{
		synchronized(widgets)
		{
			return Collections.unmodifiableList(widgets);
		}
	}
	
	@Override
	public void clear()
	{
		synchronized(widgets)
		{
			widgets.clear();
		}
	}
	
	private int getFirstPlanZIndex()
	{
		Widget lastWidget = widgets.size() == 0 ? null : widgets.getLast();
		return lastWidget == null ? 0 : lastWidget.getzIndex() + 1;
	}
	
	private Widget insertWithSort(Widget widget)
	{
		if (widget.getzIndex() == null)
		{
			widget.setzIndex(getFirstPlanZIndex());
			widgets.addLast(widget);
			widget.updateDateChange();
			return widget;
		}
		
		//get widget with first higher or equal z-index after inserted widget
		//if not found insert widget at last list position, otherwise insert to him position
		Widget tmpWidget = widgets.stream().filter(w -> w.getzIndex() >= widget.getzIndex())
						.findFirst()
						.orElse(null);
		
		if (tmpWidget == null)
		{
			widgets.addLast(widget);
		}
		else
		{
			int index = widgets.indexOf(tmpWidget);
			widgets.add(index, widget);
			//if z-index is equals increase z-index 'right' widgets
			if (tmpWidget.getzIndex() == widget.getzIndex())
			{			
				for(int i = index+1;i < widgets.size();i++)
				{
					widgets.get(i).zIndexInc();
				}			
			}
		}
		widget.updateDateChange();
		return widget;
	}
}
