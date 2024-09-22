package com.personal.beshophihi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 5, max = 50, message = "Name of discount must be length between 5 to 50 !")
    private String name;

    @Max(value = 100, message = "Amount discount must be value less 100 !")
    @Min(value = 0, message = "Amount discount must be value > 0 !")
    private int amount;

    @JsonFormat(pattern = "MM-dd-yyyy")
    private Date startDate;
    @JsonFormat(pattern = "MM-dd-yyyy")
    private Date endDate;

    @OneToMany(mappedBy = "discount", cascade = CascadeType.ALL, fetch =  FetchType.LAZY)
    private List<Product> products = new ArrayList<>();
}
