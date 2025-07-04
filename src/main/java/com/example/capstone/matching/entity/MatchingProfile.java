package com.example.capstone.matching.entity;

import com.example.capstone.user.entity.Gender;
import com.example.capstone.user.entity.UserEntity;
import com.example.capstone.util.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MatchingProfile extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserEntity user;

    @Builder.Default
    @OneToMany(mappedBy = "matchingProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MatchTravelStyle> travelStyles = new ArrayList<>();

    // 도 단위 지역
    @Enumerated(EnumType.STRING)
    private Province province;

    // 시 단위 지역
    @Builder.Default
    @OneToMany(mappedBy = "matchingProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MatchCity>  matchCities = new ArrayList<>();

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private GroupType groupType;

    @Enumerated(EnumType.STRING)
    private PreferenceGender preferenceGender;

    private int ageRange;

    public void updateProfile(LocalDate startDate, LocalDate endDate, Province province, GroupType groupType, int ageRange, PreferenceGender preferenceGender) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.province = province;
        this.groupType = groupType;
        this.ageRange = ageRange;
        this.preferenceGender = preferenceGender;
    }

}
