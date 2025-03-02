package expert.os.books.ddd.chapter10;


import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public enum StoryStatus {
    DRAFT,
    REVIEWED,
    APPROVED
}
