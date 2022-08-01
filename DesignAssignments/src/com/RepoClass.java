package com;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class RepoClass {

	private static List<Candidates> candidateList;

	static {
		prepareCandidateList();
	}

	static void prepareCandidateList() {

		candidateList = new ArrayList<>();
		candidateList.add(new Candidates("Ramesh", "Java", "Pune", 5));
		candidateList.add(new Candidates("Raman", "Java", "Banglore", 4));
		candidateList.add(new Candidates("Soumya", "C#", "Pune", 11));
		candidateList.add(new Candidates("Raghu", "Java", "Chennai", 3));
		candidateList.add(new Candidates("Pramod", "Java", "Mumbai", 1));
		candidateList.add(new Candidates("Trisha", "C#", "Pune", 0));
		candidateList.add(new Candidates("Nandan", "C++", "Chennai", 0));
		candidateList.add(new Candidates("Jeevan", "Java", "Mumbai", 1));
		candidateList.add(new Candidates("Priya", "Java", "Banglore", 11));
		candidateList.add(new Candidates("Priyanka", "C++", "Chennai", 4));

	}

	public static List<Candidates> getCandidateList() {
		return candidateList;
	}

	public static void main(String[] args) {
		System.out.println("List of Pune Candidates");
		System.out.println("Candidate count per city");
		long count = candidateList.stream().filter(p -> p.getCity().contains("Pune")).count();
		System.out.println("Candiates in Pune:" + count);

		long count1 = candidateList.stream().filter(p -> p.getCity().contains("Banglore")).count();
		System.out.println("Candiates in Bangalore:" + count1);

		long count2 = candidateList.stream().filter(p -> p.getCity().contains("Chennai")).count();
		System.out.println("Candiates in Chennai:" + count2);

		long count3 = candidateList.stream().filter(p -> p.getCity().contains("Mumbai")).count();
		System.out.println("Candiates in Mumbai:" + count3);

		System.out.println("Candidate count by Technical Expertise");
		long count4 = candidateList.stream().filter(p -> p.getTechnicalExpertise().contains("Java")).count();
		System.out.println("Candiates  with Java background:" + count4);

		long count5 = candidateList.stream().filter(p -> p.getTechnicalExpertise().contains("C++")).count();
		System.out.println("Candiates  with C++ background:" + count5);

		long count6 = candidateList.stream().filter(p -> p.getTechnicalExpertise().contains("C#")).count();
		System.out.println("Candiates  with C# background:" + count6);

		System.out.println("Fresher Candidate list");
		candidateList.stream().filter(p -> p.getYearsOfExperience() == 0).forEach(System.out::println);

		System.out.println("Sorted List of Candidates by Experience");
		Comparator<Candidates> comp = (c1, c2) -> c1.getYearsOfExperience() > c2.getYearsOfExperience() ? 1
				: c1.getYearsOfExperience() < c2.getYearsOfExperience() ? -1 : 0;

		int maximumExp = candidateList.stream().max(comp).map(c -> c.getYearsOfExperience()).get();
		candidateList.stream().filter(candidate -> candidate.getYearsOfExperience() == maximumExp)
				.forEach(System.out::println);

		System.out.println("Sorted List of Candidates by City Name");
		candidateList.stream().sorted((o1, o2) -> o1.getCity().compareTo(o2.getCity())).forEach(System.out::println);

	}

	private static void printLine() {
		System.out.println("======================================================");
	}

}