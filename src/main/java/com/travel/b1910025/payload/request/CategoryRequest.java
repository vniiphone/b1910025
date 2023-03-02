package  com.travel.b1910025.payload.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CategoryRequest {
    @NotBlank(message = "Name is required")
	private String name;

//	@NotNull
//	private Long id;

//    @NotNull
//	private Long product_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

  
}