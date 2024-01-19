package app.controller.rest;
import lombok.Data;

@Data
public class ExportRequest {
    private String filename;
    private String header;
}