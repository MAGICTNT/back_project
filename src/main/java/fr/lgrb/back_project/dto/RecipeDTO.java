package fr.lgrb.back_project.dto;

import java.util.ArrayList;
import java.util.List;

public class RecipeDTO {
    private Integer id;
    private String title;
    private Integer numberPeople;
    private Integer duration;
    private String description;
    private Integer seen;
    private String picture;
    private String nutritionTitle;
    private String categoryTitle;
    private List<IngredientDTO> ingredients; // Champ pour stocker les ingrédients

    // Constructeurs
    public RecipeDTO() {
        this.ingredients = new ArrayList<>(); // Initialiser la liste des ingrédients
    }

    public RecipeDTO(Integer id, String title, Integer numberPeople, Integer duration, String description,
                     Integer seen, String picture, String nutritionTitle, String categoryTitle) {
        this.id = id;
        this.title = title;
        this.numberPeople = numberPeople;
        this.duration = duration;
        this.description = description;
        this.seen = seen;
        this.picture = picture;
        this.nutritionTitle = nutritionTitle;
        this.categoryTitle = categoryTitle;
        this.ingredients = new ArrayList<>(); // Initialiser la liste des ingrédients
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

    public Integer getNumberPeople() {
        return numberPeople;
    }

    public void setNumberPeople(Integer numberPeople) {
        this.numberPeople = numberPeople;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSeen() {
        return seen;
    }

    public void setSeen(Integer seen) {
        this.seen = seen;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getNutritionTitle() {
        return nutritionTitle;
    }

    public void setNutritionTitle(String nutritionTitle) {
        this.nutritionTitle = nutritionTitle;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }
}
