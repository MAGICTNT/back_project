package fr.lgrb.back_project.dto;

public class NutritionDTO {
    private Integer id;
    private String title;
    private String description;

    // Constructeurs
    public NutritionDTO() {}

    public NutritionDTO(Integer id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    // Getters et Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
