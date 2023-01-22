package assignments;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

import assignments.annotations.FullNameProcessorGeneratorAnnotation;
import assignments.annotations.ListIteratorAnnotation;
import assignments.annotations.ReadFullProcessorNameAnnotation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalProcessor {
    private String processorName;
    private Long period = 10000000000000L;
    private StringBuilder processorVersion;
    private Integer valueOfCheap;
    private Scanner informationScanner;
    private List<String> stringArrayList = new LinkedList<>();

    public LocalProcessor(String processorName, Long period, StringBuilder processorVersion, Integer valueOfCheap,
                          Scanner informationScanner, ArrayList<String> stringArrayList) {
        this.processorName = processorName;
        this.period = period;
        this.processorVersion = processorVersion;
        this.valueOfCheap = valueOfCheap;
        this.informationScanner = informationScanner;
        this.stringArrayList = stringArrayList;
    }

    public LocalProcessor() {
    }

    @ListIteratorAnnotation
    public void listIterator(List<String> stringList) {
        stringList
                .stream()
                .filter(Objects::nonNull)
                .mapToInt(Objects::hashCode)
                .forEach(System.out::println);
    }

    @FullNameProcessorGeneratorAnnotation
    public String fullNameProcessorGenerator(List<String> stringList) {
        return stringList
                .stream()
                .filter(Objects::nonNull)
                .peek(s -> processorVersion.append(s))
                .collect(Collectors.joining());
    }

    @ReadFullProcessorNameAnnotation
    public void readFullProcessorName(File file){
        try {
            informationScanner = new Scanner(file);
            while (informationScanner.hasNext()) {
                processorVersion.append(informationScanner.nextLine());
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } finally {
            if (informationScanner != null){
                informationScanner.close();
            }
        }
    }
}
