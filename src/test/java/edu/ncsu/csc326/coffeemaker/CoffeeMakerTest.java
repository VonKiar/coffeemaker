/*
 * Copyright (c) 2009,  Sarah Heckman, Laurie Williams, Dright Ho
 * All Rights Reserved.
 *
 * Permission has been explicitly granted to the University of Minnesota
 * Software Engineering Center to use and distribute this source for
 * educational purposes, including delivering online education through
 * Coursera or other entities.
 *
 * No warranty is given regarding this software, including warranties as
 * to the correctness or completeness of this software, including
 * fitness for purpose.
 *
 *
 * Modifications
 * 20171114 - Ian De Silva - Updated to comply with JUnit 4 and to adhere to
 * 							 coding standards.  Added test documentation.
 */
package edu.ncsu.csc326.coffeemaker;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

/**
 * Unit tests for CoffeeMaker class.
 *
 * @author Sarah Heckman
 */
public class CoffeeMakerTest {

	/**
	 * The object under test.
	 */
	private CoffeeMaker coffeeMaker;

	private CoffeeMaker mockCoffeeMaker;
	private Inventory inventory;
	private RecipeBook recipeBook;

	// Sample recipes to use in testing.
	private Recipe recipe1;
	private Recipe recipe2;
	private Recipe recipe3;
	private Recipe recipe4;
	private Recipe recipe5;
	private Recipe recipe6;
	private Recipe recipe7;
	private Recipe recipe8;

	private Recipe[] recipeList;
	private Recipe[] MoreRecipeList;

	/**
	 * Initializes some recipes to test with and the {@link CoffeeMaker}
	 * object we wish to test.
	 *
	 * @throws RecipeException  if there was an error parsing the ingredient
	 * 		amount when setting up the recipe.
	 */
	@Before
	public void setUp() throws RecipeException {
		inventory = new Inventory();
		recipeBook = mock(RecipeBook.class);

		coffeeMaker = new CoffeeMaker();

		mockCoffeeMaker = new CoffeeMaker(recipeBook, inventory);

		//Set up for r1
		recipe1 = new Recipe();
		recipe1.setName("Coffee");
		recipe1.setAmtChocolate("0");
		recipe1.setAmtCoffee("3");
		recipe1.setAmtMilk("1");
		recipe1.setAmtSugar("1");
		recipe1.setPrice("50");

		//Set up for r2
		recipe2 = new Recipe();
		recipe2.setName("Mocha");
		recipe2.setAmtChocolate("20");
		recipe2.setAmtCoffee("3");
		recipe2.setAmtMilk("1");
		recipe2.setAmtSugar("1");
		recipe2.setPrice("75");

		//Set up for r3
		recipe3 = new Recipe();
		recipe3.setName("Latte");
		recipe3.setAmtChocolate("0");
		recipe3.setAmtCoffee("3");
		recipe3.setAmtMilk("3");
		recipe3.setAmtSugar("1");
		recipe3.setPrice("100");

		//Set up for r4
		recipe4 = new Recipe();
		recipe4.setName("Hot Chocolate");
		recipe4.setAmtChocolate("4");
		recipe4.setAmtCoffee("0");
		recipe4.setAmtMilk("1");
		recipe4.setAmtSugar("1");
		recipe4.setPrice("65");

		//Set up for r5
		recipe5 = new Recipe();
		recipe5.setName("ChocoLATE");
		recipe5.setAmtChocolate("100");
		recipe5.setAmtCoffee("0");
		recipe5.setAmtMilk("0");
		recipe5.setAmtSugar("0");
		recipe5.setPrice("100");

		//Set up for r6
		recipe6 = new Recipe();
		recipe6.setName("CoffeeLATE");
		recipe6.setAmtChocolate("0");
		recipe6.setAmtCoffee("100");
		recipe6.setAmtMilk("0");
		recipe6.setAmtSugar("0");
		recipe6.setPrice("100");

		//Set up for r7
		recipe7 = new Recipe();
		recipe7.setName("MilkLATE");
		recipe7.setAmtChocolate("0");
		recipe7.setAmtCoffee("0");
		recipe7.setAmtMilk("100");
		recipe7.setAmtSugar("0");
		recipe7.setPrice("100");

		//Set up for r8
		recipe8 = new Recipe();
		recipe8.setName("SugarLATE");
		recipe8.setAmtChocolate("0");
		recipe8.setAmtCoffee("0");
		recipe8.setAmtMilk("0");
		recipe8.setAmtSugar("100");
		recipe8.setPrice("100");

		recipeList = new Recipe[] {recipe1, recipe2, recipe3, recipe4, recipe5, recipe6, recipe7, recipe8};
	}

	/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory with well-formed quantities
	 * Then we do not get an exception trying to read the inventory quantities.
	 *
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test(expected = InventoryException.class)
	public void testAddInventory() throws InventoryException {
		coffeeMaker.addInventory("4","7","0","9");
	}

	@Test(expected = InventoryException.class)
	public void testAddInventoryNegativeCoffee() throws InventoryException {
		coffeeMaker.addInventory("-1", "1", "1", "1");
	}

	@Test(expected = InventoryException.class)
	public void testAddInventoryNegativeMilk() throws InventoryException {
		coffeeMaker.addInventory("1", "-1", "1", "1");
	}

	@Test(expected = InventoryException.class)
	public void testAddInventoryNonIntegerMilk() throws InventoryException {
		coffeeMaker.addInventory("1", "Aloha", "1", "1");
	}

	@Test(expected = InventoryException.class)
	public void testAddInventoryNegativeSugar() throws InventoryException {
		coffeeMaker.addInventory("1", "1", "-1", "1");
	}

	@Test(expected = InventoryException.class)
	public void testAddInventoryPositiveSugar() throws InventoryException {
		coffeeMaker.addInventory("1", "1", "1", "1");
	}

	@Test(expected = InventoryException.class)
	public void testAddInventoryNonIntegerSugar() throws InventoryException {
		coffeeMaker.addInventory("1", "1", "SAWASDEE", "1");
	}

	@Test(expected = InventoryException.class)
	public void testAddInventoryNonIntegerCoffee() throws InventoryException {
		coffeeMaker.addInventory("hola", "1", "1","1");
	}

	@Test
	public void testAddInventoryNotEnoughChocolate() throws RecipeException {
		coffeeMaker.addRecipe(recipe5);
		assertEquals(100, coffeeMaker.makeCoffee(0, 100));
	}

	@Test
	public void testAddInventoryNotEnoughCoffee() throws RecipeException {
		coffeeMaker.addRecipe(recipe6);
		assertEquals(100, coffeeMaker.makeCoffee(0, 100));
	}

	@Test
	public void testAddInventoryNotEnoughMilk() throws RecipeException {
		coffeeMaker.addRecipe(recipe7);
		assertEquals(100, coffeeMaker.makeCoffee(0, 100));
	}

	@Test
	public void testAddInventoryNotEnoughSugar() throws RecipeException {
		coffeeMaker.addRecipe(recipe8);
		assertEquals(100, coffeeMaker.makeCoffee(0, 100));
	}

	/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory with malformed quantities (i.e., a negative
	 * quantity and a non-numeric string)
	 * Then we get an inventory exception
	 *
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test(expected = InventoryException.class)
	public void testAddInventoryException() throws InventoryException {
		coffeeMaker.addInventory("4", "-1", "aaaa", "3");
	}

	/**
	 * Given a coffee maker with one valid recipe
	 * When we make coffee, selecting the valid recipe and paying more than
	 * the coffee costs
	 * Then we get the correct change back.
	 */
	@Test
	public void testMakeCoffee() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(25, coffeeMaker.makeCoffee(0, 75));
		coffeeMaker.addRecipe(recipe2);
		assertEquals(25, coffeeMaker.makeCoffee(1, 25));
		coffeeMaker.addRecipe(recipe3);
		assertEquals(0, coffeeMaker.makeCoffee(2, 100));
		coffeeMaker.addRecipe(recipe4);
		assertEquals(50, coffeeMaker.makeCoffee(3, 50));
	}

	/**
	 * Testing add 4th recipe to the recipes.
	 */
	@Test
	public void testAddRecipe() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		coffeeMaker.addRecipe(recipe3);
		coffeeMaker.addRecipe(recipe4); // This should not be added.
		assertEquals(3, coffeeMaker.getRecipes().length);
	}

	/**
	 * Test delete recipes.
	 */
	@Test
	public void testDeleteRecipe() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(recipe1, coffeeMaker.getRecipes()[0]); // Check recipe1.
		coffeeMaker.deleteRecipe(0); // Delete recipe1.
		assertNotEquals(recipe1, coffeeMaker.getRecipes()[0]); // Check again.
	}

	/**
	 * Testing editing recipes, throws exceptions when error occurs.
	 */
	@Test
	public void testEditRecipe() throws RecipeException {
		assertEquals(null, coffeeMaker.editRecipe(0, recipe2));

		coffeeMaker.addRecipe(recipe1); // Initial object.

		Recipe edited = new Recipe(); // Testing object for editing recipe.
		edited.setName("Edited");
		edited.setAmtChocolate("1");
		edited.setAmtCoffee("2");

		assertEquals(coffeeMaker.getRecipes()[0], recipe1); // Verifying initial object.

		coffeeMaker.editRecipe(0, edited); // Editing.

		// Verifying edited object.
		assertEquals(coffeeMaker.getRecipes()[0].getAmtCoffee(), edited.getAmtCoffee());
		assertEquals(coffeeMaker.getRecipes()[0], edited);
	}

	@Test(expected = InventoryException.class)
	public void testAddChocolate() throws InventoryException {
		coffeeMaker.addInventory("0", "0","0","ABC");
	}

	/**
	 * Testing purchases of coffee, check changes.
	 */
	@Test
	public void testPurchaseBeverage() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(50, coffeeMaker.makeCoffee(0, 100));
		assertEquals("Coffee: 12\nMilk: 14\nSugar: 14\nChocolate: 15\n", coffeeMaker.checkInventory());
	}

	@Test
	public void testMakeCoffeeWithMock1() {
		when(mockCoffeeMaker.getRecipes()).thenReturn(recipeList);
		assertEquals(25, mockCoffeeMaker.makeCoffee(0, 75));
		assertEquals(25, mockCoffeeMaker.makeCoffee(1, 25));
		assertEquals(0, mockCoffeeMaker.makeCoffee(2, 100));
		assertEquals(50, mockCoffeeMaker.makeCoffee(3, 50));
	}

	@Test
	public void testPurchaseBeverageMock() {
		when(mockCoffeeMaker.getRecipes()).thenReturn(recipeList);
		assertEquals(50, mockCoffeeMaker.makeCoffee(0, 100));
		assertEquals("Coffee: 12\nMilk: 14\nSugar: 14\nChocolate: 15\n", mockCoffeeMaker.checkInventory());
	}
}