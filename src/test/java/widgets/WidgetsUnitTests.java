package widgets;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import widgets.Data.Widget;
import widgets.Storage.IStorage;
import widgets.Storage.RamStorage;

public class WidgetsUnitTests {

	private IStorage storage;

	@Before
	public void Init() {
		this.storage = new RamStorage();
	}

	@Test
	public void insertWidget() {
		storage.clear();
		Widget widget = new Widget(1, 1, 10, 10, 5);
		Widget insertedWidget = storage.insert(widget);
		Boolean containsInStorage = storage.get(insertedWidget.getId()) != null;

		assertTrue(insertedWidget != null && widget.equals(insertedWidget) && insertedWidget.getId() != null
				&& containsInStorage);
	}

	@Test
	public void deleteWidget() {
		storage.clear();

		Widget widget = new Widget(1, 1, 10, 10, 5);
		Widget insertedWidget = storage.insert(widget);
		Boolean containsInStorage = storage.get(insertedWidget.getId()) != null;
		if (!containsInStorage) {
			fail("Insert error");
		}
		storage.delete(insertedWidget.getId());
		containsInStorage = storage.get(insertedWidget.getId()) == null;

		assertTrue(containsInStorage);
	}

	@Test
	public void updateWidget() {
		storage.clear();
		Widget widget = new Widget(1, 1, 10, 10, 5);
		Widget insertedWidget = storage.insert(widget);
		Boolean containsInStorage = storage.get(insertedWidget.getId()) != null;
		if (!containsInStorage) {
			fail("Insert error");
		}
		insertedWidget.setHeight(50);
		insertedWidget.setWidth(50);

		storage.update(insertedWidget);
		Widget updatedWidget = storage.get(insertedWidget.getId());
		if (updatedWidget == null) {
			fail("Update error");
		}

		assertTrue(insertedWidget.equals(updatedWidget));
	}

	@Test
	public void insertSomeWidgetsWithoutShift() {
		storage.clear();
		Widget w1 = storage.insert(new Widget(1, 1, 10, 10, 5));
		Widget w2 = storage.insert(new Widget(1, 1, 10, 10, 2));
		Widget w3 = storage.insert(new Widget(1, 1, 10, 10, null));
		Widget w4 = storage.insert(new Widget(1, 1, 10, 10, 3));

		assertTrue(storage.get(w1.getId()).getzIndex() == 5 && storage.get(w2.getId()).getzIndex() == 2
				&& storage.get(w3.getId()).getzIndex() == 6 && storage.get(w4.getId()).getzIndex() == 3);
	}

	@Test
	public void insertSomeWidgetsWithoutShift1() {
		storage.clear();
		Widget w1 = storage.insert(new Widget(1, 1, 10, 10, null));
		Widget w2 = storage.insert(new Widget(1, 1, 10, 10, null));
		Widget w3 = storage.insert(new Widget(1, 1, 10, 10, null));
		Widget w4 = storage.insert(new Widget(1, 1, 10, 10, null));

		assertTrue(storage.get(w1.getId()).getzIndex() == 0 && storage.get(w2.getId()).getzIndex() == 1
				&& storage.get(w3.getId()).getzIndex() == 2 && storage.get(w4.getId()).getzIndex() == 3);
	}

	@Test
	public void insertSomeWidgetsWithShift() {
		storage.clear();
		Widget w1 = storage.insert(new Widget(1, 1, 10, 10, 1));
		Widget w2 = storage.insert(new Widget(1, 1, 10, 10, 1));
		Widget w3 = storage.insert(new Widget(1, 1, 10, 10, 1));
		Widget w4 = storage.insert(new Widget(1, 1, 10, 10, 1));

		assertTrue(storage.get(w1.getId()).getzIndex() == 4 && storage.get(w2.getId()).getzIndex() == 3
				&& storage.get(w3.getId()).getzIndex() == 2 && storage.get(w4.getId()).getzIndex() == 1);
	}

	@Test
	public void insertSomeWidgetsWithShift1() {
		storage.clear();
		Widget w1 = storage.insert(new Widget(1, 1, 10, 10, 5));
		Widget w2 = storage.insert(new Widget(1, 1, 10, 10, 2));
		Widget w3 = storage.insert(new Widget(1, 1, 10, 10, null));
		Widget w4 = storage.insert(new Widget(1, 1, 10, 10, 2));

		assertTrue(storage.get(w1.getId()).getzIndex() == 6 && storage.get(w2.getId()).getzIndex() == 3
				&& storage.get(w3.getId()).getzIndex() == 7 && storage.get(w4.getId()).getzIndex() == 2);
	}
	
	@Test
	public void getAll() {
		storage.clear();
		storage.insert(new Widget(1, 1, 10, 10, 5));
		storage.insert(new Widget(1, 1, 10, 10, 2));
		storage.insert(new Widget(1, 1, 10, 10, null));
		storage.insert(new Widget(1, 1, 10, 10, 2));

		assertTrue(storage.getAll().size() == 4);
	}
}
