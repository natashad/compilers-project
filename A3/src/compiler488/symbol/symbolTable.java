package csc488;

import java.util.ArrayList;
import java.util.HashMap;

//TODO: Might want to merge minor scopes with major scopes to save space and time.

public class symbolTable {
	
	/* Depth is the current scope level
	 * scopeTable is a list of hashmaps which contain the symbols for each scope (mapping is "symbol name -> symbol")
	 */
	
	int depth = -1;
	ArrayList<HashMap<String, Symbol>> scopeTable = new ArrayList<HashMap<String, Symbol>>();
	
	
	/*
	 * Open scope creates a new hashmap for the current scope and adds it to scopeTable
	 */
	void openScope()
	{
		depth++;
		HashMap<String, Symbol> temp = new HashMap<String, Symbol>();
		scopeTable.add(temp);
	}
	
	/*
	 * closeScope removes the hashmap of the current scope and decrements the depth, so we are pointing
	 * to the previous scope
	 */
	void closeScope()
	{
		if (depth == -1)
			throw new IllegalArgumentException("There are no scopes to close");
		scopeTable.remove(depth);
		depth--;
	}
	
	/*
	 * getSymbol looks up a symbol by name using the scope rule, we first look in the current scope for the 
	 * symbol, if we don't find it we go up the scopeTable until we find it, if we don't we return null.
	 */
	Symbol getSymbol(String name)
	{
		for (int i = depth; i >= 0; i++)
		{
			Symbol result = (scopeTable.get(i)).get(name);
			if (result != null)
			{
				return result;
			}
		}	
		return null;
	}
	
	/*
	 * getCurrentScopeSymbol looks in the current scope for the symbol. If we don't find it return null.
	 */
	Symbol getCurrentScopeSymbol(String name)
	{
		Symbol result = (scopeTable.get(depth)).get(name);
		return result;

	}
	
	/*
	 * enterSymbol adds a symbol to the current scope.
	 */
	void enterSymbol(Symbol sym)
	{
		if (getCurrentScopeSymbol(sym.name) == null)
			(scopeTable.get(depth)).put(sym.name, sym);
		else
			throw new IllegalArgumentException("Duplicate definition of " + sym.name);
	}
	
}

