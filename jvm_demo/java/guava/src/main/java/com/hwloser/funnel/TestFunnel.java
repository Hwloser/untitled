package com.hwloser.funnel;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import java.util.Random;
import java.util.UUID;

public class TestFunnel {

  public static void main(String[] args) {
    long id = 1234L;
    String name = "name";
    Person person = new Person(id, name);

    testHashMd5(person);

    testBloomFilter();
  }

  private static void testBloomFilter() {
    BloomFilter<Person> friends = BloomFilter.create(((from, into) -> {
      into.putLong(from.id).putString(
          from.firstName,
          Charsets.UTF_8
      );
    }), 500, 0.01);

    friends.put(getRandomPerson());
    friends.put(getRandomPerson());
    friends.put(getRandomPerson());
    Person detectPerson = getRandomPerson();

    System.out.println(friends.mightContain(detectPerson));;

    friends.put(detectPerson);
    System.out.println(friends.mightContain(detectPerson));;
  }


  private static Person getRandomPerson() {
    Random r = new Random();
    long id = r.nextLong();
    String name = UUID.randomUUID().toString().replace("-", "");
    return new Person(id, name);
  }

  private static void testHashMd5(Person person) {
    HashFunction hf = Hashing.md5();
    HashCode hc = hf.newHasher()
        .putLong(person.id)
        .putString(person.firstName, Charsets.UTF_8)
        .putObject(person, ((from, into) -> {
          into.putLong(from.id).putString(
              from.firstName,
              Charsets.UTF_8
          );
        }))
        .hash();

    System.out.println(hc);
  }

  static class Person {

    long id;
    String firstName;

    public Person(long id, String firstName) {
      this.id = id;
      this.firstName = firstName;
    }
  }
}
