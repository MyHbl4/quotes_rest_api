package com.moon.quotes_rest_api.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "votes")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "quote_id")
    private Quote quote;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Integer voice;
    private LocalDate dateCreate = LocalDate.now();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vote vote = (Vote) o;

        if (getId() != null ? !getId().equals(vote.getId()) : vote.getId() != null) {
            return false;
        }
        if (getQuote() != null ? !getQuote().equals(vote.getQuote()) : vote.getQuote() != null) {
            return false;
        }
        if (getUser() != null ? !getUser().equals(vote.getUser()) : vote.getUser() != null) {
            return false;
        }
        if (getVoice() != null ? !getVoice().equals(vote.getVoice()) : vote.getVoice() != null) {
            return false;
        }
        return getDateCreate() != null ? getDateCreate().equals(vote.getDateCreate())
            : vote.getDateCreate() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getQuote() != null ? getQuote().hashCode() : 0);
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        result = 31 * result + (getVoice() != null ? getVoice().hashCode() : 0);
        result = 31 * result + (getDateCreate() != null ? getDateCreate().hashCode() : 0);
        return result;
    }
}
