<?php

namespace model;

class Scientist
{
    private int $id;
    private string $name;
    private string $firstName;
    private string $photo;
    private string $description;
    private Theme $theme;
    private Difficulty $difficulty;

    /**
     * @param int $id
     * @param string $name
     * @param string $firstName
     * @param string $photo
     * @param string $description
     * @param Theme $theme
     * @param Difficulty $difficulty
     */
    public function __construct(int $id, string $name, string $firstName, string $photo, string $description, Theme $theme, Difficulty $difficulty)
    {
        $this->id = $id;
        $this->name = $name;
        $this->firstName = $firstName;
        $this->photo = $photo;
        $this->description = $description;
        $this->theme = $theme;
        $this->difficulty = $difficulty;
    }

    /**
     * @return int
     */
    public function getId(): int
    {
        return $this->id;
    }

    /**
     * @return string
     */
    public function getName(): string
    {
        return $this->name;
    }

    /**
     * @param string $name
     */
    public function setName(string $name): void
    {
        $this->name = $name;
    }

    /**
     * @return string
     */
    public function getFirstName(): string
    {
        return $this->firstName;
    }

    /**
     * @param string $firstName
     */
    public function setFirstName(string $firstName): void
    {
        $this->firstName = $firstName;
    }

    /**
     * @return string
     */
    public function getPhoto(): string
    {
        return $this->photo;
    }

    /**
     * @param string $photo
     */
    public function setPhoto(string $photo): void
    {
        $this->photo = $photo;
    }

    /**
     * @return string
     */
    public function getDescription(): string
    {
        return $this->description;
    }

    /**
     * @param string $description
     */
    public function setDescription(string $description): void
    {
        $this->description = $description;
    }

    /**
     * @return Theme
     */
    public function getTheme(): Theme
    {
        return $this->theme;
    }

    /**
     * @param Theme $theme
     */
    public function setTheme(Theme $theme): void
    {
        $this->theme = $theme;
    }

    /**
     * @return Difficulty
     */
    public function getDifficulty(): Difficulty
    {
        return $this->difficulty;
    }

    /**
     * @param Difficulty $difficulty
     */
    public function setDifficulty(Difficulty $difficulty): void
    {
        $this->difficulty = $difficulty;
    }
}