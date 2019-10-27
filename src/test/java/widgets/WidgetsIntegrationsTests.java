package widgets;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import widgets.DTO.WidgetDTO;
import widgets.Data.Widget;
import widgets.Storage.IStorage;
import widgets.Storage.RamStorage;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class WidgetsIntegrationsTests {
	
    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IStorage storage;

    @Before
    public void init() {
    	storage = new RamStorage();
    }

    @Test
    public void createWidget_OK() throws Exception {
    	WidgetDTO widgetDto = new WidgetDTO(1,2,5,3,4);

    	Widget widget = new Widget(1,2,3,4,5);
    	when(storage.insert(any(Widget.class))).thenReturn(widget);    

        mockMvc.perform(post("/widget")
                .content(om.writeValueAsString(widgetDto))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.xPosition").value(widget.getxPosition()))
                .andExpect(jsonPath("$.yPosition").value(widget.getyPosition()))
                .andExpect(jsonPath("$.id").value(widget.getId().toString()))
                .andExpect(jsonPath("$.width").value(widget.getWidth()))
                .andExpect(jsonPath("$.height").value(widget.getHeight()))
                .andExpect(jsonPath("$.zIndex").value(widget.getzIndex()));
    }
    
    @Test
    public void createWidget_400() throws Exception {
    	WidgetDTO widgetDto = new WidgetDTO(null,1,10,10,10);

        mockMvc.perform(post("/widget")
                .content(om.writeValueAsString(widgetDto))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    public void getWidget_OK() throws Exception {
    	Widget widget = new Widget(1,2,3,4,5);
    	when(storage.get(any(UUID.class))).thenReturn(widget);    

        mockMvc.perform(get(String.format("/widget/%s", widget.getId().toString())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.xPosition").value(widget.getxPosition()))
                .andExpect(jsonPath("$.yPosition").value(widget.getyPosition()))
                .andExpect(jsonPath("$.id").value(widget.getId().toString()))
                .andExpect(jsonPath("$.width").value(widget.getWidth()))
                .andExpect(jsonPath("$.height").value(widget.getHeight()))
                .andExpect(jsonPath("$.zIndex").value(widget.getzIndex()));
    }
    
    @Test
    public void deleteWidget_OK() throws Exception {
    	
    	UUID id = UUID.randomUUID();
        mockMvc.perform(delete(String.format("/widget/%s", id.toString())))
                .andExpect(status().isNotFound());
    }
}
