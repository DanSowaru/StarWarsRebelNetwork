package br.com.letscode.starwarsrebelnetwork.service;

import br.com.letscode.starwarsrebelnetwork.dto.LocalizationDTO;
import br.com.letscode.starwarsrebelnetwork.dto.ReportSummaryDTO;
import br.com.letscode.starwarsrebelnetwork.entity.InventoryEntity;
import br.com.letscode.starwarsrebelnetwork.entity.InventoryItemEntity;
import br.com.letscode.starwarsrebelnetwork.entity.RebelEntity;
import br.com.letscode.starwarsrebelnetwork.enums.Gender;
import br.com.letscode.starwarsrebelnetwork.enums.Item;
import br.com.letscode.starwarsrebelnetwork.enums.RebelStatus;
import br.com.letscode.starwarsrebelnetwork.repository.RebelRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RebelReportServiceTest {
    @Mock
    private RebelRepository repository;

    private RebelReportService reportService ;
    private RebelService rebelService;
    InventoryService inventoryService = new InventoryService();


    @BeforeAll
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getReportFromTraitors() {
        when(repository.getTraitorsList()).thenReturn(List.of(buildEntity("Danilo"), buildEntity("Rubens")));
        when(repository.getAll()).thenReturn(List.of(buildEntity("Aramiz"), buildEntity("Danilo"), buildEntity("Rubens")));

        rebelService = new RebelService(repository, inventoryService);
        reportService = new RebelReportService(repository, rebelService);


        ReportSummaryDTO report = reportService.getSummaryReport(RebelStatus.TRAITOR);
        assertEquals(report.getTotal(), 2);
        assertEquals(report.getPercentage(), "66,7%");
        assertEquals(report.getStatus(), RebelStatus.TRAITOR);

    }

    @Test
    void getAlliesReport() {
        when(repository.getAlliesList()).thenReturn(List.of(buildEntity("Danilo")));
        when(repository.getAll()).thenReturn(List.of(buildEntity("Aramiz"), buildEntity("Amanda")));

        rebelService = new RebelService(repository, inventoryService);
        reportService = new RebelReportService(repository, rebelService);


        ReportSummaryDTO report = reportService.getSummaryReport(RebelStatus.ALLY);
        assertEquals(report.getTotal(), 1);
        assertEquals(report.getPercentage(), "50%");
        assertEquals(report.getStatus(), RebelStatus.ALLY);
    }

    private RebelEntity buildEntity(String name) {
        var localization = new LocalizationDTO(generateNumberRandom(100), generateNumberRandom(100), "Florianópolis", generateNumberRandom(10000));

        InventoryEntity entity = new InventoryEntity();
        InventoryItemEntity item1 = new InventoryItemEntity(Item.WEAPON, Integer.parseInt(generateNumberRandom(10)));
        InventoryItemEntity item2 = new InventoryItemEntity(Item.FOOD, Integer.parseInt(generateNumberRandom(10)));
        entity.setItensEntity(List.of(item1, item2));


        return new RebelEntity(name, generateNumberRandom(100), Gender.MALE, localization, entity, Short.parseShort(generateNumberRandom(10)), generateNumberRandom(500));

    }

    private String generateNumberRandom(Integer scale) {
        return Long.toString(Math.round(scale * Math.random()));
    }
}