package com.moon.quotes_rest_api.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "quotes")
public class Quote implements Comparable<Quote> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Integer score = 0;
    @OneToMany(mappedBy = "quote", cascade = CascadeType.ALL, orphanRemoval = true)
    @Exclude
    private List<Vote> vote = new ArrayList<>();
    private LocalDate dateCreate = LocalDate.now();
    private LocalDate dateUpdate = LocalDate.now();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Quote quote = (Quote) o;

        if (getId() != null ? !getId().equals(quote.getId()) : quote.getId() != null) {
            return false;
        }
        if (getDescription() != null ? !getDescription().equals(quote.getDescription())
            : quote.getDescription() != null) {
            return false;
        }
        if (getUser() != null ? !getUser().equals(quote.getUser()) : quote.getUser() != null) {
            return false;
        }
        if (getScore() != null ? !getScore().equals(quote.getScore()) : quote.getScore() != null) {
            return false;
        }
        if (getVote() != null ? !getVote().equals(quote.getVote()) : quote.getVote() != null) {
            return false;
        }
        if (getDateCreate() != null ? !getDateCreate().equals(quote.getDateCreate())
            : quote.getDateCreate() != null) {
            return false;
        }
        return getDateUpdate() != null ? getDateUpdate().equals(quote.getDateUpdate())
            : quote.getDateUpdate() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        result = 31 * result + (getScore() != null ? getScore().hashCode() : 0);
        result = 31 * result + (getVote() != null ? getVote().hashCode() : 0);
        result = 31 * result + (getDateCreate() != null ? getDateCreate().hashCode() : 0);
        result = 31 * result + (getDateUpdate() != null ? getDateUpdate().hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Quote o) {
        return this.getScore()-o.getScore();
    }
}
