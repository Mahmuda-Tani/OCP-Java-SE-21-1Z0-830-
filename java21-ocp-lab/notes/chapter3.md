# Chapter 3 Study Guide — Making Decisions

**Book:** *OCP Oracle Certified Professional Java SE 21 Developer Study Guide* (Boyarsky & Selikoff) — Book Chapter 3  
**Exam:** 1Z0-830 — objective: **Controlling Program Flow**  
**Lab folder:** `com.tani.ocp.chapter3`

---

## Why this chapter matters

At least one construct from this chapter appears in many exam questions with sample code. A large share of “Does not compile” questions come from **syntax**, **unreachable code**, **switch rules**, and **pattern matching / flow scoping**.

---

## 1. Statements and blocks

- A **statement** ends with `;` and is one unit of execution.
- A **block** is `{ ... }` and can stand anywhere a single statement is allowed.
- The **target** of `if`, `while`, `for`, etc. can be one statement or a block.

```java
if (ticketsTaken > 1)
    patrons++;           // only this line is conditional

if (ticketsTaken > 1) {
    patrons++;           // both lines are conditional
    logVisit();
}
```

**Exam trap:** Indentation does **not** define scope — only braces do.

---

## 2. `if` and `else`

| Rule | Detail |
|------|--------|
| Condition | Must be **boolean** — not `int` (`if (hourOfDay)` does not compile) |
| `else if` | Evaluated only if previous conditions were false |
| `else` | Optional final branch |

**Style:** Prefer blocks `{ }` even for one line (easier to extend, clearer on the exam).

### Brace trap (very common on exam)

```java
if (hourOfDay < 11)
    System.out.println("Good Morning");
morningGreetingCount++;   // ALWAYS runs — not inside the if!
```

---

## 3. Pattern matching with `if` and `instanceof` (Java 16+, refined in 21)

**Old style:**

```java
if (number instanceof Integer) {
    Integer data = (Integer) number;
    System.out.print(data.compareTo(5));
}
```

**Pattern matching:**

```java
if (number instanceof Integer data) {
    System.out.print(data.compareTo(5));
}
```

- `data` is the **pattern variable**.
- Cast happens only when `instanceof` is true (no `ClassCastException` from the check itself).
- `null` → `instanceof` is **false** (all three forms below print nothing):

```java
if (noObjectHere instanceof String) { }
if (noObjectHere instanceof String s) { }
if (noObjectHere instanceof String s && s.length() > -1) { }
```

### Guard (conditional clause)

```java
if (number instanceof Integer data && data.compareTo(5) > 0)
    System.out.print(data);
```

Use **`&&`**, not **`||`**, when the pattern variable is used on the right:

```java
// DOES NOT COMPILE — data may be undefined
if (number instanceof Integer data || data.compareTo(5) > 0)
```

### Supported pattern types

- Same type, **subtype**, or **supertype** of the reference (Java 21 allows same/supertype; older rules were stricter).
- **Unrelated** type → does not compile: `Number n; ... instanceof String s`

### Flow scoping

Pattern variable is in scope only where the compiler can prove the type:

```java
if (number instanceof Integer data)
    System.out.print(data.intValue());
System.out.print(data.intValue()); // DOES NOT COMPILE — out of scope
```

```java
if (!(number instanceof Integer data))
    return;
System.out.print(data.intValue()); // OK — compiler knows data is Integer here
```

**Do not reassign** pattern variables (confusing scope). `final` pattern variable cannot be reassigned.

---

## 4. `switch` — statement vs expression

| | Switch **statement** | Switch **expression** |
|---|---------------------|----------------------|
| Returns value? | No | **Yes** — used in `return`, assignment |
| Typical separator | `:` + `break` | `->` (arrow) |
| Semicolon after whole switch | Usually no | Often yes (`var x = switch ...;`) |
| Case expressions | N/A | Each `->` value needs `;` when not in a block |

### Supported switch types

`int`, `Integer`, `byte`, `Byte`, `short`, `Short`, `char`, `Character`, `String`, **enum**, **`var`** (if it resolves to one of these), and **any object** when using **pattern matching**.

**Not supported:** `boolean`, `long`, `float`, `double`

### Case values must be compile-time constants

- Literals, enum constants, `final` variables initialized with a **literal** in the same declaration.
- **Not allowed:** non-final locals, method calls, `final int x = getValue();`

```java
final int bananas = 1;   // OK in case
int apples = 2;          // NOT OK in case
case 3 * 5:              // OK — compile-time expression
```

Case type must **match** switch variable type.

### Colon `:` vs arrow `->`

- **Statement with `:`:** fall-through unless `break` — multiple cases can run.
- **Arrow `->`:** no fall-through for that branch.
- **Cannot mix** `:` and `->` in the same switch.

### Multi-value cases

```java
case 2, 3:    // OK
case 1: 13:   // NOT OK — use comma, not second colon
```

### Empty switch

```java
switch (month) { }   // valid
```

---

## 5. Switch expressions — syntax checklist

```java
return switch (type) {
    case 0 -> "Lion";
    case 2, 3 -> "Alligator";
    default -> "Unknown";
};  // semicolon after closing brace when used in return/assignment
```

Common compile errors:

- Missing parentheses: `switch food` → need `switch (food)`
- Missing `;` after each `->` branch
- Missing `;` after entire expression in assignment
- Missing `{ }` around switch **body** in a statement form

---

## 6. Pattern matching with `switch` (Java 21 — high exam priority)

```java
String message = switch (height) {
    case Integer i when i > 10 -> "Rounded large: " + i;
    case Integer i -> "Rounded: " + i;
    case Double d -> "Precise: " + d;
    case Number n -> "Unknown: " + n;
};
```

| Concept | Rule |
|---------|------|
| `when` | Guard on a case — required with pattern + condition |
| Ordering | **Matters** — first matching branch wins; broader case before specific → **dominated / unreachable** |
| Types | Same or subtype of switch reference — unrelated types do not compile |
| Exhaustiveness | With pattern matching, **statements** must be exhaustive too (not only expressions) |
| Cover all values | `default`, or last case with **same reference type** as switch variable |

**Dominance example (does not compile):**

```java
switch (height) {
    case Number n -> ...;
    case Integer i -> ...;  // unreachable — never reached
}
```

**Guard ordering (does not compile):**

```java
case Integer i -> "Daniel";
case Integer i when i > 10 -> "Joseph";  // second branch unreachable
```

### `case null` (Java 21)

- `switch` on null without `case null` → **NullPointerException** at runtime (even with `default`).
- `case null` avoids extra `if (x == null)` wrapper.
- Using `case null` implies **pattern matching** → switch must be **exhaustive**.
- `case null` cannot appear **after** `default`.

---

## 7. `while` and `do-while`

| Loop | Condition checked | Body runs |
|------|-------------------|-----------|
| `while` | Before each iteration | **0+** times |
| `do-while` | After each iteration | **At least 1** time |

```java
int full = 5;
while (full < 5) {   // condition false immediately — body never runs
    System.out.println("Not full!");
}
```

**Infinite loop trap:** loop variable never moves toward termination → compiles but runs forever.

---

## 8. `for` loop

```java
for (initialization; booleanExpression; update) {
    // body
}
```

- Variables declared in **initialization** are scoped **only inside the loop**.
- Variables declared **before** the loop can be used in initialization and **after** the loop.

```java
for (int i = 0; i < 5; i++)
    System.out.print(i);
System.out.println(i);  // DOES NOT COMPILE
```

### Reverse counting

Print `4 3 2 1 0`:

```java
for (var counter = 4; counter >= 0; counter--) {
    System.out.print(counter + " ");
}
```

---

## 9. Enhanced `for` (for-each)

```java
for (String item : list) { }
for (int row : matrix[0]) { }
```

**Permitted:** arrays, `String`, types implementing `Iterable` (e.g. `List`)  
**Not for-each directly:** `Map` (iterate `entrySet()` etc.), plain `Object`, most primitives without array

Compiler builds the boolean condition for you.

---

## 10. `break`, `continue`, `return`, labels

### Table 3.1 — what each construct supports

| Construct | Labels | `break` | `continue` | `yield` | `when` |
|-----------|--------|---------|------------|---------|--------|
| `if/else` | No | No | No | No | No |
| `do-while` | Yes | Yes | Yes | No | No |
| `for` | Yes | Yes | Yes | No | No |
| `switch` | Yes | Yes | No | Yes | Yes |
| `while` | Yes | Yes | Yes | No | No |

- **`break`:** exits loop or switch (or labeled outer structure).
- **`continue`:** ends **current iteration**, goes to next condition check.
- **Labeled `break` / `continue`:** `OUTER: for (...) { break OUTER; }`

### Unreachable code

Any statement **immediately after** `break`, `continue`, or `return` in the same block → **does not compile**, even if the condition is never true at runtime.

---

## 11. Exam essentials checklist

- [ ] Trace **braces** on `if` — indentation lies.
- [ ] `if` condition is **boolean** only.
- [ ] Pattern variable scope: **`&&`**, early `return`, negated `instanceof`.
- [ ] Switch types: no `boolean`/`long`/`float`/`double`.
- [ ] Case values: **compile-time constants**, matching type.
- [ ] Statement `:` vs `->`, fall-through, `break`.
- [ ] Expression: semicolons, yields consistent type, exhaustive branches.
- [ ] Pattern switch: **order**, **exhaustive**, **`when`**, **`case null`**, no `null` after `default`.
- [ ] `while` (0+) vs `do-while` (1+).
- [ ] `for` init variable scope.
- [ ] for-each: **Iterable** / array only.
- [ ] Labels + nested loops: which loop `break`/`continue` affects.
- [ ] Unreachable code after `break`/`continue`/`return`.

---

## 12. Runnable examples in this lab

| Topic | Class |
|-------|--------|
| if / else / else-if | `chapter3.ifelse.IfElseBasicsExample` |
| Brace trap | `chapter3.ifelse.IfElseBraceTrapExample` |
| Pattern matching if | `chapter3.patternmatching.InstanceofPatternMatchingExample` |
| Flow scoping | `chapter3.patternmatching.FlowScopingExample` |
| Switch statement | `chapter3.switchstmt.SwitchStatementExample` |
| Switch expression | `chapter3.switchstmt.SwitchExpressionExample` |
| Case constants | `chapter3.switchstmt.SwitchCaseConstantsExample` |
| Pattern switch + when | `chapter3.switchstmt.SwitchPatternMatchingExample` |
| case null | `chapter3.switchstmt.SwitchNullCaseExample` |
| while | `chapter3.loops.WhileLoopExample` |
| do-while | `chapter3.loops.DoWhileLoopExample` |
| for | `chapter3.loops.ForLoopExample` |
| for-each | `chapter3.loops.ForEachLoopExample` |
| break / continue | `chapter3.branching.BreakContinueExample` |
| Labeled break | `chapter3.branching.LabeledBreakContinueExample` |

Run any example from `App.java` or directly:

```bash
mvn compile exec:java -Dexec.mainClass=com.tani.ocp.chapter3.switchstmt.SwitchExpressionExample
```

---

## 13. Study plan before the exam

1. Read each section above once.
2. Run every example; change values and predict output.
3. For each example, ask: “What if I remove braces / semicolon / break?”
4. Do book Chapter 3 review questions — aim for 100% before Chapter 4.
5. Redo questions you missed; note **compile vs runtime** vs **logic error**.

**Status:** Ready for exam practice on Making Decisions.
