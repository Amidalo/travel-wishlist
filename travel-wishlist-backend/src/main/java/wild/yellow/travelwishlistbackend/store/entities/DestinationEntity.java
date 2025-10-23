package wild.yellow.travelwishlistbackend.store.entities;

import jakarta.persistence.*;
import lombok.*;
import wild.yellow.travelwishlistbackend.enums.DestinationStatus;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "destination")
public class DestinationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private DestinationStatus status = DestinationStatus.PLANNED;

    @ManyToOne
    @JoinColumn(name = "consumer_id", nullable = false)
    private ConsumerEntity consumer;

    @Column(name = "created_at",
            nullable = false,
            updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

}
