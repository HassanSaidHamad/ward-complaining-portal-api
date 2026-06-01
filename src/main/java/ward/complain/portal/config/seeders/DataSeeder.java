package ward.complain.portal.config.seeders;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ward.complain.portal.models.ComplaintCategory;
import ward.complain.portal.models.District;
import ward.complain.portal.models.Region;
import ward.complain.portal.models.Ward;
import ward.complain.portal.repository.ComplaintCategoryRepository;
import ward.complain.portal.repository.DistrictRepository;
import ward.complain.portal.repository.RegionRepository;
import ward.complain.portal.repository.WardRepository;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {
    private final RegionRepository regionRepository;
    private final DistrictRepository districtRepository;
    private final WardRepository wardRepository;
    private final ComplaintCategoryRepository categoryRepository;


    @Override
    public void run(String... args) throws Exception {
        try {
            seedComplaintCategories();
            seedRegionsDistrictsAndWards();
        } catch (Exception e) {
            System.out.println("Seeding failed: " + e.getMessage());
        }
    }


    private void seedComplaintCategories() {
        if (categoryRepository.count() > 0) {
            return;
        }

        log.info("STARTING CATEGORY DATA SEEDING...");

        seedCategory("Waters");
        seedCategory("Roads");
        seedCategory("Electricity");
        seedCategory("Garbage");
        seedCategory("Drainage");
        seedCategory("Security");
    }

    private void seedRegionsDistrictsAndWards() {

        if (regionRepository.count() > 0) {
            return;
        }

        log.info("STARTING ZANZIBAR DATA SEEDING...");


        // =====================================================
        // MJINI MAGHARIBI REGION
        // =====================================================

        Region mjiniMagharibi = regionRepository.save(
                Region.builder()
                        .regionName("Mjini Magharibi")
                        .build()
        );


        // ---------------- Mjini District ----------------

        District mjiniDistrict = districtRepository.save(
                District.builder()
                        .districtName("Mjini")
                        .region(mjiniMagharibi)
                        .build()
        );

        seedWard("Malindi", mjiniDistrict);
        seedWard("Mkunazini", mjiniDistrict);
        seedWard("Shaurimoyo", mjiniDistrict);
        seedWard("Kikwajuni", mjiniDistrict);


        // ---------------- Magharibi A District ----------------
        District magharibiA = districtRepository.save(
                District.builder()
                        .districtName("Magharibi A")
                        .region(mjiniMagharibi)
                        .build()
        );

        seedWard("Bububu", magharibiA);
        seedWard("Chuini", magharibiA);
        seedWard("Fuoni", magharibiA);


        // ---------------- Magharibi B District ----------------
        District magharibiB = districtRepository.save(
                District.builder()
                        .districtName("Magharibi B")
                        .region(mjiniMagharibi)
                        .build()
        );

        seedWard("Dole", magharibiB);
        seedWard("Kiembesamaki", magharibiB);
        seedWard("Mbweni", magharibiB);


        // =====================================================
        // KASKAZINI UNGUJA REGION
        // =====================================================
        Region kaskaziniUnguja = regionRepository.save(
                Region.builder()
                        .regionName("Kaskazini Unguja")
                        .build()
        );
        // ---------------- Kaskazini A ----------------
        District kaskaziniA = districtRepository.save(
                District.builder()
                        .districtName("Kaskazini A")
                        .region(kaskaziniUnguja)
                        .build()
        );

        seedWard("Nungwi", kaskaziniA);
        seedWard("Kendwa", kaskaziniA);
        seedWard("Matemwe", kaskaziniA);


        // ---------------- Kaskazini B ----------------
        District kaskaziniB = districtRepository.save(
                District.builder()
                        .districtName("Kaskazini B")
                        .region(kaskaziniUnguja)
                        .build()
        );
        seedWard("Mkokotoni", kaskaziniB);
        seedWard("Mahonda", kaskaziniB);
        seedWard("Donge", kaskaziniB);


        // =====================================================
        // KUSINI UNGUJA REGION

        Region kusiniUnguja = regionRepository.save(
                Region.builder()
                        .regionName("Kusini Unguja")
                        .build()
        );


        // ---------------- Kati District ----------------
        District kati = districtRepository.save(
                District.builder()
                        .districtName("Kati")
                        .region(kusiniUnguja)
                        .build()
        );
        seedWard("Koani", kati);
        seedWard("Bungi", kati);
        seedWard("Dunga", kati);


        // ---------------- Kusini District ----------------
        District kusini = districtRepository.save(
                District.builder()
                        .districtName("Kusini")
                        .region(kusiniUnguja)
                        .build()
        );
        seedWard("Makunduchi", kusini);
        seedWard("Paje", kusini);
        seedWard("Jambiani", kusini);

        // =====================================================
        // KASKAZINI PEMBA REGION
        // =====================================================
        Region kaskaziniPemba = regionRepository.save(
                Region.builder()
                        .regionName("Kaskazini Pemba")
                        .build()
        );


        District micheweni = districtRepository.save(
                District.builder()
                        .districtName("Micheweni")
                        .region(kaskaziniPemba)
                        .build()
        );
        seedWard("Konde", micheweni);
        seedWard("Shumba Mjini", micheweni);
        District wete = districtRepository.save(
                District.builder()
                        .districtName("Wete")
                        .region(kaskaziniPemba)
                        .build()
        );

        seedWard("Wete", wete);
        seedWard("Gando", wete);


        // =====================================================
        // KUSINI PEMBA REGION
        // =====================================================
        Region kusiniPemba = regionRepository.save(
                Region.builder()
                        .regionName("Kusini Pemba")
                        .build()
        );

        District chakeChake = districtRepository.save(
                District.builder()
                        .districtName("Chake Chake")
                        .region(kusiniPemba)
                        .build()
        );
        seedWard("Chake Chake", chakeChake);
        seedWard("Mtoni", chakeChake);


        District mkoani = districtRepository.save(
                District.builder()
                        .districtName("Mkoani")
                        .region(kusiniPemba)
                        .build()
        );
        seedWard("Mkoani", mkoani);
        seedWard("Kangagani", mkoani);
        log.info("ZANZIBAR DATA SEEDED SUCCESSFULLY.");

    }


    private void seedWard(String wardName, District district) {
        wardRepository.save(
                Ward.builder()
                        .wardName(wardName)
                        .district(district)
                        .build()
        );
    }

    private void  seedCategory(String categoryName) {
        categoryRepository.save(
                ComplaintCategory.builder()
                        .categoryName(categoryName)
                        .build()
        );
    }

}
