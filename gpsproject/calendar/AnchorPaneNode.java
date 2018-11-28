package GPS.gpsproject.calendar;

import GPS.Modelo.Evento;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.util.List;

/**
 * Create an anchor pane that can store additional data.
 */
public class AnchorPaneNode extends AnchorPane {

    // Date associated with this pane
    private LocalDate date;
    private List<Evento> eventos;
    private TextArea detailsdia;
    /**
     * Create a anchor pane node. Date is not assigned in the constructor.
     * @param children children of the anchor pane
     */
    public AnchorPaneNode(TextArea detailsdia, List<Evento> eventos, Node... children) {
        super(children);

        this.eventos = eventos;
        this.detailsdia = detailsdia;
        // Add action handler for mouse clicked
        this.setOnMouseClicked(e -> setDetails());
    }

    private void setDetails() {
        String text;

        if(eventos.size() == 0) {
            detailsdia.setText("Dia sem eventos");
            return;
        }

        for (Evento evento : eventos) {
            //text += evento.getDescricao();
            text = "Teste";
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
