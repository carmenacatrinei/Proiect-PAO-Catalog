package ro.catalog.entitati;

import ro.catalog.dao.repositories.MateriiRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Catalog {
    private int id;
    private List<Materie> materii = new ArrayList<>();
    private List<Student> studenti = new ArrayList<>();
    private int grupa;
    private List<List<Integer>> note;

    public Catalog(int grupa) {
        this.grupa = grupa;
    }

    public void adaugareMaterie(Materie materie){
        materii.add(materie);
    }

    public void adaugareStudent(Student student){
        if(student.getGrupa() == grupa) {
            studenti.add(student);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMateriiSize(){
        return materii.size();
    }

    public int getStudentiSize(){

        return studenti.size();
    }

    public int getGrupa() {
        return grupa;
    }

    public List<Materie> getMaterii() {
        return materii;
    }

    public void setMaterii(List<Materie> materii) {
        this.materii = materii;
    }

    public Student getStudentFromIndex(int index){
        return studenti.get(index);
    }

    public List<Student> getStudenti(){ return studenti; }

    public List<List<Integer>> getNote(){
        return note;
    }

    public void setNote(List<List<Integer>> note) {
        this.note = note;
    }

    public void setNota(Integer nota, Student student, Materie materie){
        note.get(studenti.indexOf(student)).set(materii.indexOf(materie), nota);
    }

    public String afisareCatalog(){
        StringBuilder buffer = new StringBuilder();
        buffer.append("           ");
        for (Materie materie: materii) {
            buffer.append(materie.getDenumire()).append(" ");
        }
        buffer.append("\n");
        for (Student student: studenti) {
            buffer.append(student.getNume()).append(" ").append(student.getPrenume()).append(" ");
            List<Integer> noteStudent = note.get(studenti.indexOf(student));
            for (Integer nota: noteStudent) {
                buffer.append(nota).append(" ");
            }
            buffer.append("\n");
        }
        return buffer.toString();
    }

    public void afisareNoteStudent(Student student){
        List<Integer> noteStudent = note.get(studenti.indexOf(student) - 1);

        MateriiRepository materiiRepository = MateriiRepository.getMateriiRepository();

        List<Materie> materii = materiiRepository.select();

        for (int i = 0; i < materii.size(); i++) {
            String concat = materii.get(i).getDenumire().concat(":").concat(Integer.toString(noteStudent.get(i)));
            System.out.println(concat);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Catalog)) return false;
        Catalog catalog = (Catalog) o;
        return grupa == catalog.grupa && Objects.equals(materii, catalog.materii) && Objects.equals(studenti, catalog.studenti);
    }

    @Override
    public int hashCode() {
        return Objects.hash(materii, studenti, grupa);
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "materii=" + materii +
                ", studenti=" + studenti +
                ", grupa=" + grupa +
                '}';
    }


}
