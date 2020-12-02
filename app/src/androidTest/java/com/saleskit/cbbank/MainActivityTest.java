package com.saleskit.cbbank;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.saleskit.cbbank.util.ErrorTestUtil;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import java.util.List;

import io.mvpstarter.sample.R;

import com.saleskit.cbbank.common.TestComponentRule;
import com.saleskit.cbbank.common.TestDataFactory;
import com.saleskit.cbbank.data.model.response.NamedResource;
import com.saleskit.cbbank.data.model.response.Pokemon;
import com.saleskit.cbbank.features.main.MainActivity;

import io.reactivex.Single;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private final TestComponentRule componentRule =
            new TestComponentRule(InstrumentationRegistry.getTargetContext());
    private final ActivityTestRule<MainActivity> mainActivityTestRule =
            new ActivityTestRule<>(MainActivity.class, false, false);

    // TestComponentRule needs to go first to make sure the Dagger ApplicationTestComponent is set
    // in the Application before any Activity is launched.
    @Rule
    public TestRule chain = RuleChain.outerRule(componentRule).around(mainActivityTestRule);

    @Test
    public void checkPokemonsDisplay() {
        List<NamedResource> namedResourceList = TestDataFactory.makeNamedResourceList(5);
        List<String> pokemonList = TestDataFactory.makePokemonNameList(namedResourceList);
        stubDataManagerGetPokemonList(Single.just(pokemonList));
        mainActivityTestRule.launchActivity(null);

        for (NamedResource pokemonName : namedResourceList) {
            onView(withText(pokemonName.name)).check(matches(isDisplayed()));
        }
    }

    @Test
    public void clickingPokemonLaunchesDetailActivity() {
        List<NamedResource> namedResourceList = TestDataFactory.makeNamedResourceList(5);
        List<String> pokemonList = TestDataFactory.makePokemonNameList(namedResourceList);
        stubDataManagerGetPokemonList(Single.just(pokemonList));
        stubDataManagerGetPokemon(Single.just(TestDataFactory.makePokemon("id")));
        mainActivityTestRule.launchActivity(null);

        onView(withText(pokemonList.get(0))).perform(click());

        onView(ViewMatchers.withId(R.id.image_pokemon)).check(matches(isDisplayed()));
    }

    @Test
    public void checkErrorViewDisplays() {
        stubDataManagerGetPokemonList(Single.error(new RuntimeException()));
        mainActivityTestRule.launchActivity(null);
        ErrorTestUtil.checkErrorViewsDisplay();
    }

    public void stubDataManagerGetPokemonList(Single<List<String>> single) {
        when(componentRule.getMockApiManager().getPokemonList(anyInt())).thenReturn(single);
    }

    public void stubDataManagerGetPokemon(Single<Pokemon> single) {
        when(componentRule.getMockApiManager().getPokemon(anyString())).thenReturn(single);
    }
}
